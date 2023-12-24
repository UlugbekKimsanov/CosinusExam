package com.example.cosinusexam.LibrarySystem.service;

import com.example.cosinusexam.LibrarySystem.dto.Response.KitobResponseDto;
import com.example.cosinusexam.LibrarySystem.entity.Kitob;
import com.example.cosinusexam.LibrarySystem.entity.Order;
import com.example.cosinusexam.LibrarySystem.entity.UserEntity;
import com.example.cosinusexam.LibrarySystem.entity.enums.KitobStatus;
import com.example.cosinusexam.LibrarySystem.exception.DataNotFoundException;
import com.example.cosinusexam.LibrarySystem.repository.KitobRepository;
import com.example.cosinusexam.LibrarySystem.repository.OrderRepository;
import com.example.cosinusexam.LibrarySystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final KitobRepository kitobRepository;
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    public List<KitobResponseDto> getAvailableBooks() {
        List<Kitob> kitobList = kitobRepository.findByStatus(KitobStatus.ON_SHELF);
        return modelMapper.map(kitobList, new TypeToken<List<KitobResponseDto>>(){}.getType());
    }
    public String addOrder(UUID bookId, Principal principal) {
        Kitob kitob = kitobRepository.findById(bookId)
                .orElseThrow(() -> new DataNotFoundException("Kitob topilmadi!"));
        if(kitob.getStatus() == KitobStatus.ON_SHELF){
            UserEntity owner = (UserEntity) userRepository.findByEmail(principal.getName()).get();
            Order order = new Order(kitob,owner);
            kitob.setStatus(KitobStatus.ORDERED);
            orderRepository.save(order);
            kitobRepository.save(kitob);
            return "Bron qilindi!";
        } else if (kitob.getStatus() == KitobStatus.ORDERED){
            Order order = orderRepository.findByKitob_Id(bookId);
            if (order.getExpiryDate().isAfter(LocalDateTime.now())){
                orderRepository.delete(order);
                kitob.setStatus(KitobStatus.ON_SHELF);
                kitobRepository.save(kitob);
                return "Bron qilindi!";
            }
        }
        return "Ushbu kitob ijarada!";
    }

    public String  getRent(UUID bookId, Principal principal) {
        Order order = orderRepository.findByKitob_Id(bookId);
        if(Objects.equals(null,order)){
            return "Kitob topilmadi!";
        }else if(order.getExpiryDate().isAfter(LocalDateTime.now())){
            orderRepository.delete(order);
            Kitob kitob = kitobRepository.findById(order.getKitob().getId()).get();
            kitob.setStatus(KitobStatus.RENTED);
            kitobRepository.save(kitob);
            return "Kitob olib ketildi!";
        }
        return "Bu kitob bron qilinmagan!";
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
    public List<Order> getOrders(Principal principal) {
        return orderRepository.findAllByOwnerEmail(principal.getName());
    }
}

package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Artist;
import com.example.capstone2.Model.Note;
import com.example.capstone2.Model.OrderHistory;
import com.example.capstone2.Model.Order_Rate;
import com.example.capstone2.Repository.ArtistRepository;
import com.example.capstone2.Repository.NoteRepository;
import com.example.capstone2.Repository.OrderRateRepository;
import com.example.capstone2.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final OrderRepository orderRepository;
    private final OrderRateRepository orderRateRepository;
    private final NoteRepository noteRepository;

    public void addArtist(Artist artist){
        artistRepository.save(artist);

    }

    public List<Artist> getAllArtist(){
        return artistRepository.findAll();
    }

    public void updateArtist(Integer artistId, Artist artist){
        Artist a = artistRepository.findArtistByArtistId(artistId);
        if(a==null){
            throw new ApiException("artist not found");
        }
        a.setName(artist.getName());
        a.setUsername(artist.getUsername());
        a.setPassword(artist.getPassword());
        a.setEmail(artist.getEmail());
        a.setPhoneNumber(artist.getPhoneNumber());
        artistRepository.save(a);
    }

    public Artist getArtistById(Integer artistId){
        return artistRepository.findArtistByArtistId(artistId);
    }

    public void removeArtist(Integer artistId){
        boolean doesArtistExists = artistRepository.existsById(artistId);
        if(!doesArtistExists){
            throw new ApiException("artist does not exists");
        }
        artistRepository.deleteById(artistId);
    }

    public List<OrderHistory> getOrdersByArtistId(Integer artistId){
        boolean doesArtistExists = artistRepository.existsById(artistId);
        if(!doesArtistExists){
            throw new ApiException("artist does not exists");
        }
        return orderRepository.findOrdersByArtistId(artistId);
    }

    public void setOrderAsDone(Integer artistId, Integer orderId){
        boolean doesArtistExists = artistRepository.existsById(artistId);
        if(!doesArtistExists){
            throw new ApiException("artist does not exists");
        }
        OrderHistory order = orderRepository.findOrderByOrderId(orderId);
        if(order == null){
            throw new ApiException("order does not exists");
        }
        if(!order.getArtistId().equals(artistId)){
            throw new ApiException("order does not belong to artist");
        }
        order.setStatus("done");
        orderRepository.save(order);
    }

    public List<Note> getAllNoteByOrderId(Integer artistId,Integer orderId){
        Artist artist = artistRepository.findArtistByArtistId(artistId);
        if(artist == null){
            throw new ApiException("artist does not exists");
        }
        OrderHistory order = orderRepository.findOrderByOrderId(orderId);
        if(!order.getArtistId().equals(artistId)){
            throw new ApiException("order does not belong to the artist");
        }
        return noteRepository.findNotesByOrderId(orderId);
    }

    public Note viewNote(Integer artistId, Integer noteId){
        Artist artist = artistRepository.findArtistByArtistId(artistId);
        if(artist == null){
            throw new ApiException("artist does not exists");
        }
        Note note = noteRepository.findNoteByNoteId(noteId);
        if(note == null){
            throw new ApiException("note does not exists");
        }
        OrderHistory order = orderRepository.findOrderByOrderId(note.getOrderId());
        if(!order.getArtistId().equals(artistId)){
            throw new ApiException("note does not belong to artist");
        }
        return note;
    }

    public double artistAverageRate(Integer artistId){
        Artist artist = artistRepository.findArtistByArtistId(artistId);
        if(artist == null){
            throw new ApiException("artist does not exists");
        }
        double artistAverageRate = 0;
        double counter = 0;
        List<OrderHistory> orders = orderRepository.findOrdersByArtistId(artistId);
        for(OrderHistory order : orders){
            if(order.getArtistId().equals(artistId)){
                artistAverageRate += orderRateRepository.findOrder_RateByOrderId(order.getOrderId()).getRate();
                counter++;
            }
        }
        return artistAverageRate/counter;
    }

    public Artist highestRatedArtist(){
        List<OrderHistory> orders = orderRepository.findAll();
        List<Artist> artists = artistRepository.findAll();

        Artist highestRatedArtist = null;
        double averageRate = 0;
        for(Artist artist:artists){
            double temp = 0;
            double counter = 0;
            for(OrderHistory order:orders){
                if(order.getArtistId().equals(artist.getArtistId())){
                    temp += orderRateRepository.findOrder_RateByOrderId(order.getOrderId()).getRate();
                    counter++;
                }
                temp = temp/counter;
                if(averageRate<temp){
                    highestRatedArtist = artist;
                }
            }
        }

        return highestRatedArtist;
    }


    public List<Artist> searchForArtist(String keyword){
        List<Artist> artists = new ArrayList<>();
        for(Artist artist:artistRepository.findAll()){
            if(artist.getName().toLowerCase().contains(keyword.toLowerCase())
                    ||artist.getDescription().toLowerCase().contains(keyword.toLowerCase())){
                artists.add(artist);
            }
        }
        return artists;
    }

    public Artist loginCheck(String username, String password){
        Artist artist = artistRepository.findArtistByUsername(username);
        if(artist==null){
            throw new ApiException("invalid username");
        }
        if(!artist.getPassword().equals(password)){
            throw new ApiException("invalid username");
        }
        return artist;
    }
    public void setAcceptOrder(Integer artistId,boolean choice){
        Artist artist = artistRepository.findArtistByArtistId(artistId);
        if(artist == null){
            throw new ApiException("artist does not exists");
        }
        artist.setDoesAcceptOrder(choice);
        artistRepository.save(artist);
    }
    public List<Artist> getAllArtistWhereArtistAcceptOrder() {
        return artistRepository.findArtistsByDoesAcceptOrderTrue();
    }
}

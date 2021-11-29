package com.tiozao.AppDependsService.AppDependsService.application.controller;

import com.tiozao.AppDependsService.AppDependsService.application.controller.model.BagDto;
import com.tiozao.AppDependsService.AppDependsService.application.controller.model.BagItem;
import com.tiozao.AppDependsService.AppDependsService.application.domain.BagServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class BagController {

    private BagServices bagServices;

    public BagController(BagServices bagServices){
        this.bagServices = bagServices;
    }

    @GetMapping("/{id_profile}/bag")
    public ResponseEntity<BagDto> findBag(@PathParam("id_profile") String id_profile){
        BagDto bag = new BagDto();
        return ResponseEntity.ok(bag);
    }

    @PostMapping("/{id_profile}/bag/itens")
    public ResponseEntity<BagItem> addItemBag(
            @PathParam("id_profile") String id_profile,
            @RequestBody BagItem item
    ){
       return ResponseEntity.ok(bagServices.addItemBag(id_profile,item));
    }

}

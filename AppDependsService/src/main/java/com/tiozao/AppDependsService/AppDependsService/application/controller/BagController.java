package com.tiozao.AppDependsService.AppDependsService.application.controller;

import com.tiozao.AppDependsService.AppDependsService.application.controller.model.BagDto;
import com.tiozao.AppDependsService.AppDependsService.application.controller.model.BagItem;
import com.tiozao.AppDependsService.AppDependsService.application.domain.BagServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BagController {

    private BagServices bagServices;

    public BagController(BagServices bagServices){
        this.bagServices = bagServices;
    }

    @GetMapping("/{id_profile}/bag")
    public ResponseEntity<BagDto> findBag(
            @PathVariable("id_profile") String id_profile){
        BagDto bag = bagServices.findBag(id_profile);
        return ResponseEntity.ok(bag);
    }

    @PostMapping("/{id_profile}/bag/itens")
    public ResponseEntity<BagItem> addItemBag(
            @PathVariable("id_profile") String id_profile,
            @RequestBody BagItem item
    ){
       return ResponseEntity.ok(bagServices.addItemBag(id_profile,item));
    }

}

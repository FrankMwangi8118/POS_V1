package com.codify.ioio.Controller;

import com.codify.ioio.Commons.ApiResponse;
import com.codify.ioio.Model.TblSales;
import com.codify.ioio.Services.PosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pos/v1")
public class PosControllers {
    private final PosService posService;

    public PosControllers(PosService posService) {
        this.posService = posService;
    }

    @GetMapping("/totalCustomers")
    public ResponseEntity<ApiResponse> getName(){
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .responseCode("200")
                        .responseMessage("success")
                        .results(posService.getAllPayments())
                        .build()
        );
    }
    @GetMapping("/homeController")
    public String name(){
        return "name";
    }

    @GetMapping("/allCustomers")
    public ResponseEntity<ApiResponse> allCustomers(){
        return ResponseEntity.ok(
          ApiResponse.builder()
                  .intResponse(posService.getCustomerBaseSize())
                  .build()
        );
    }
    @GetMapping("/stock")
    public ResponseEntity<ApiResponse> allStock(){
        return ResponseEntity.ok(
          ApiResponse.builder()
                  .results(posService.allStock())
                  .responseCode("200")
                  .responseMessage("all remaining stock")
                  .build()
        );
    }


    @GetMapping("/totalSales")
    public ResponseEntity<ApiResponse>allSales(){
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .responseCode("200")
                        .intResponse(posService.salesCalc())
                        .responseMessage("all sales")
                        .build()
        );
    }
    @GetMapping("/allSales")
    public ResponseEntity<ApiResponse> allSale() {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .responseMessage("total sales")
                        .responseCode("200")
                        .results(posService.allSales())
                        .build()
        );

    }
    @GetMapping("/filterSales")
    public ResponseEntity<ApiResponse>filterSales(@RequestParam String filterParam){
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .responseCode("200")
                        .responseMessage("filtered payments")
                        .results(posService.filterSales(filterParam))
                        .build()
        );
    }
    @GetMapping("/filterPayments")
    public ResponseEntity<ApiResponse>allPayment(@RequestParam String filterParam){
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .responseCode("200")
                        .responseMessage("filtered payments")
                        .results(posService.filterPayment(filterParam))
                        .build()
        );
    }


}


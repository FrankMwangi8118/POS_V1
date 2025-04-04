package com.codify.ioio.Controller;

import com.codify.ioio.Commons.ApiResponse;
import com.codify.ioio.Model.TblSales;
import com.codify.ioio.Services.PosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController

public class PosControllers {
    private final PosService posService;

    public PosControllers(PosService posService) {
        this.posService = posService;
    }

    @GetMapping("/allPayments")
    public ResponseEntity<ApiResponse> getName(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "15")int size) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .responseCode("200")
                        .responseMessage("success")
                        .results(posService.getAllPayments())
                        .build()
        );
    }

    @GetMapping("/dashData")
    public ResponseEntity<ApiResponse> dashData() {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .responseCode("200")
                        .responseMessage("dash data")
                        .results(posService.getDashData(
                                posService.getTotalStockPrice(posService.allStock()),
                                posService.getTotalSales(posService.allSales()),
                                posService.getTotalPayments(posService.getAllPayments()),
                                posService.getCustomerBaseSize()

                        ))

                        .build()
        );
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse> filterDash(@RequestParam String filterParam) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .responseCode("200")
                        .responseMessage("filterData")
                        .results(posService.filterDashData(filterParam))
                        .build()
        );
    }

    @GetMapping("/totalCustomers")
    public ResponseEntity<ApiResponse> allCustomers() {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .responseCode("200")
                        .responseMessage("all customers")
                        .intResponse(posService.getCustomerBaseSize())
                        .build()
        );
    }

    

    @GetMapping("/stock")
    public ResponseEntity<ApiResponse> allPageableStock(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .results(posService.allPageableStock(page, size))
                        .responseCode("200")
                        .responseMessage("all remaining stock")
                        .build()
        );
    }


    @GetMapping("/allSales")
    public ResponseEntity<ApiResponse> allSale(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "15")int size) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .responseMessage("total sales")
                        .responseCode("200")
                        .results(posService.allSales())
                        .build()
        );

    }

    @GetMapping("/filterSales")
    public ResponseEntity<ApiResponse> filterSales(@RequestParam String filterParam)  {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .responseCode("200")
                        .responseMessage("filtered sales")
                        .results(posService.filterSales(filterParam))
                        .build()
        );
    }

    @GetMapping("/filterPayments")
    public ResponseEntity<ApiResponse> allPayment(@RequestParam String filterParam) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .responseCode("200")
                        .responseMessage("filtered payments")
                        .results(posService.filterPayment(filterParam))
                        .build()
        );
    }
//    @GetMapping("/totalStockPrice")
//    public ResponseEntity<ApiResponse>totalStockPrice(){
//        return  ResponseEntity.ok(
//          ApiResponse.builder()
//                  .responseCode("200")
//                  .responseMessage("total Stock price")
//                  .intResponse(posService.getTotalStockPrice(posService.allStock()))
//                  .build()
//        );
//    }

@GetMapping("/filterDashData")
    public ResponseEntity<ApiResponse> filterDashDataByWeek(@RequestParam String filterParam) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .results(posService.filterDashData(filterParam))
                        .build()
        );
    }
}


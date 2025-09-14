package com.sinaukoding.eventbookingsystem.controller.master;

import com.sinaukoding.eventbookingsystem.model.filter.BookingFilterRecord;
import com.sinaukoding.eventbookingsystem.model.filter.EventFilterRecord;
import com.sinaukoding.eventbookingsystem.model.request.BookingRequestRecord;
import com.sinaukoding.eventbookingsystem.model.response.BaseResponse;
import com.sinaukoding.eventbookingsystem.service.master.BookingService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("booking")
@RequiredArgsConstructor
@Tag(name = "Booking API")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("save")
//    @PreAuthorize("hasRole('PENJUAL')")
    public BaseResponse<?> save(@RequestBody BookingRequestRecord request) {
        bookingService.add(request);
        return BaseResponse.ok("Data added successfully", null);
    }

    @PostMapping("edit")
//    @PreAuthorize("hasRole('PENJUAL')")
    public BaseResponse<?> edit(@RequestBody BookingRequestRecord request) {
        bookingService.edit(request);
        return BaseResponse.ok("Data updated successfully", null);
    }

    @PostMapping("find-all")
    @Parameters({
            @Parameter(name = "page", description = "Page Number", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "0"), required = true),
            @Parameter(name = "size", description = "Size Per Page", in = ParameterIn.QUERY, schema = @Schema(type = "integer", defaultValue = "10"), required = true),
            @Parameter(name = "sort", description = "Sorting Data", in = ParameterIn.QUERY, schema = @Schema(type = "string", defaultValue = "modifiedDate,desc"), required = true)
    })
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody BookingFilterRecord filterRequest) {
        return BaseResponse.ok(null, bookingService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, bookingService.findById(id));
    }

}
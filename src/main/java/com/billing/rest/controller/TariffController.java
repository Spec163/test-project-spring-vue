package com.billing.rest.controller;


import com.billing.rest.domain.Tariff;
import com.billing.rest.repository.TariffRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tariff")
public class TariffController {
    private final TariffRepository tariffRepository;

    @Autowired
    public TariffController(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @GetMapping
    public List<Tariff> list() {
        return tariffRepository.findAll();
    }

    @GetMapping("{id}")
    public Tariff getOne(@PathVariable("id") Tariff tariff) {
        return tariff;
    }

    @PostMapping
    public Tariff create(@RequestBody Tariff tariff) {
        return tariffRepository.save(tariff);
    }

    @PutMapping("{id}")
    public Tariff update(
            @PathVariable("id") Tariff tariffFromDb,
            @RequestBody Tariff tariff
    ) {
        BeanUtils.copyProperties(tariff, tariffFromDb, "id");

        return tariffRepository.save(tariffFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Tariff tariff) {
        tariffRepository.delete(tariff);
    }
}

package com.billing.rest.controller;


import com.billing.rest.domain.Tariff;
import com.billing.rest.domain.Views;
import com.billing.rest.dto.EventType;
import com.billing.rest.dto.ObjectType;
import com.billing.rest.repository.TariffRepository;
import com.billing.rest.util.WsSender;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("tariff")
public class TariffController {
    private final TariffRepository tariffRepository;
    private final BiConsumer<EventType, Tariff> wsSender;

    @Autowired
    public TariffController(TariffRepository tariffRepository, WsSender wsSender) {
        this.tariffRepository = tariffRepository;
        this.wsSender = wsSender.getSender(ObjectType.TARIFF, Views.IdInfo.class);
    }

    @GetMapping
    @JsonView(Views.IdInfo.class)
    public List<Tariff> list() {
        return tariffRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Tariff getOne(@PathVariable("id") Tariff tariff) {
        return tariff;
    }

    @PostMapping
    public Tariff create(@RequestBody Tariff tariff) {
        tariff.setCreationDate(LocalDateTime.now());
        Tariff updatedTariff = tariffRepository.save(tariff);

        wsSender.accept(EventType.CREATE, updatedTariff);

        return updatedTariff;
    }

    @PutMapping("{id}")
    public Tariff update(
            @PathVariable("id") Tariff tariffFromDb,
            @RequestBody Tariff tariff
    ) {
        BeanUtils.copyProperties(tariff, tariffFromDb, "id");
        Tariff updatedTariff = tariffRepository.save(tariffFromDb);

        wsSender.accept(EventType.UPDATE, updatedTariff);

        return updatedTariff;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Tariff tariff) {
        tariffRepository.delete(tariff);
        wsSender.accept(EventType.REMOVE, tariff);
    }
}

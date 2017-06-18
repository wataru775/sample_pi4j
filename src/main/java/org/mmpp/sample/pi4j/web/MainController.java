package org.mmpp.sample.pi4j.web;

import com.pi4j.io.gpio.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class MainController {

    private GpioController gpio;
    private GpioPinDigitalOutput pinLED;

    /**
     * GPIOピン初期化処理
     */
    private void initialize() {
        if (gpio == null) {
            gpio = GpioFactory.getInstance();
            // Raspberry pi の piは 16 : GPIO_27
            pinLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "LED", PinState.LOW);
        }

        pinLED.low();
    }

    @RequestMapping("/on")
    @ResponseBody
    public String turnOn() {
        initialize();
        pinLED.high();

        return "Turn on";
    }

    @RequestMapping("/off")
    @ResponseBody
    public String turnOff() {
        initialize();
        pinLED.low();

        return "Turn off";
    }

}

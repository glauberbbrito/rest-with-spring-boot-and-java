package br.com.gbb.rest_with_spring_boot_and_java.controllres;

import br.com.gbb.rest_with_spring_boot_and_java.exception.UnsuportedMathOperationException;
import br.com.gbb.rest_with_spring_boot_and_java.math.SimpleMath;
import br.com.gbb.rest_with_spring_boot_and_java.request.converters.NumberConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    private SimpleMath math = new SimpleMath();

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne,
                       @PathVariable("numberTwo") String numberTwo)  throws UnsuportedMathOperationException {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw new UnsuportedMathOperationException("Please set a numeric value");
        return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable("numberOne") String numberOne,
                       @PathVariable("numberTwo") String numberTwo)  throws UnsuportedMathOperationException {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw new UnsuportedMathOperationException("Please set a numeric value");
        return math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable("numberOne") String numberOne,
                       @PathVariable("numberTwo") String numberTwo)  throws UnsuportedMathOperationException {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw new UnsuportedMathOperationException("Please set a numeric value");
        return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable("numberOne") String numberOne,
                       @PathVariable("numberTwo") String numberTwo)  throws UnsuportedMathOperationException {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw new UnsuportedMathOperationException("Please set a numeric value");
        return math.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("/mutiplication/{numberOne}/{numberTwo}")
    public Double mutiplication(@PathVariable("numberOne") String numberOne,
                       @PathVariable("numberTwo") String numberTwo)  throws UnsuportedMathOperationException {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) throw new UnsuportedMathOperationException("Please set a numeric value");
        return math.mutiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("/square-root/{numberOne}")
    public Double squareRoot(@PathVariable("numberOne") String numberOne)  throws UnsuportedMathOperationException {
        if (!NumberConverter.isNumeric(numberOne)) throw new UnsuportedMathOperationException("Please set a numeric value");
        return math.squareRoot(NumberConverter.convertToDouble(numberOne));
    }

}

package com.cg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WithdrawCreReqDTO implements Validator {

    private String transactionAmount;

    @Override
    public boolean supports(Class<?> aClass) {
        return WithdrawCreReqDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        WithdrawCreReqDTO withdrawCreReqDTO = (WithdrawCreReqDTO) o;

        String transactionAmountStr = withdrawCreReqDTO.transactionAmount;

        if (transactionAmountStr == null) {
            errors.rejectValue("transactionAmount", "transactionAmount.length", "Số tiền cần rút là bắt buộc");
            return;
        }

        if (transactionAmountStr.length() == 0) {
            errors.rejectValue("transactionAmount", "transactionAmount.length", "Vui lòng nhập số tiền muốn rút");
        }
        else {
            if (!transactionAmountStr.matches("\\d+")) {
                errors.rejectValue("transactionAmount", "transactionAmount.matches", "Vui lòng nhập giá trị tiền bằng chữ số");
            }
            else {
                BigDecimal transactionAmount = BigDecimal.valueOf(Long.parseLong(transactionAmountStr));

                if (transactionAmount.compareTo(BigDecimal.valueOf(100L)) <= 0) {
                    errors.rejectValue("transactionAmount", "transactionAmount.min", "Số tiền muốn nạp thấp nhất là $100");
                }
                else {
                    if (transactionAmount.compareTo(BigDecimal.valueOf(1000000L)) > 0) {
                        errors.rejectValue("transactionAmount", "transactionAmount.max", "Số tiền muốn nạp tối đa là $1.000.000");
                    }
                }
            }
        }
    }
}
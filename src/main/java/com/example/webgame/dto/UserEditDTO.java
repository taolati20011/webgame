package com.example.webgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDTO {

    @NotEmpty(message = "Account can not be empty")
    @Size(max = 50, min = 5, message = "Length: 5 -> 50 characters")
    private String username;

    @NotEmpty(message = "Địa chỉ can not be empty")
    @Size(max = 200, message = "Maximun length 200 characters")
    private String userAddress;

    @NotEmpty(message = "Name can not be empty")
    @Pattern(regexp = "^[a-zA-Z" +
            "àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ]+(\\s[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ]+)*$",
            message = "Name must match the following fomat: Nguyen Van A ...")
    private String userFullname;

    @NotEmpty(message = "Phone number can not be empty")
    @Pattern(
            regexp = "^(?:0|\\+84)[0-9]{8,9}$",
            message = "Phone number must match the following fomat: 090xxxxxxx or 091xxxxxxx or (84) + 90xxxxxxx or " +
                    "(84) + " +
                    "91xxxxxxx"
    )
    private String userPhone;

    @NotEmpty(message = "Email can not be empty")
    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$",
            message = "Email must match the following format: abc@xyz")
    private String userEmail;

    @NotEmpty(message = "Gender can not be empty")
    @Size(max = 8, min = 2, message = "Gender must match the following fomat:  Female or Male")
    private String userGender;
}

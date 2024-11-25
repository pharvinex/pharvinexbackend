//package com.pharvinex.pharvinexGroup.jwtModel;
//
//import lombok.*;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@ToString
//public class JwtRequest {
//
//    private String email;
//    private String password;
//}
package com.pharvinex.pharvinexGroup.jwtModel;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtRequest {
    private String email;
    private String password;
}

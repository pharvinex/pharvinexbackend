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
//public class JwtResponse {
//    private String jwtToken;
//    private String username;
//}
package com.pharvinex.pharvinexGroup.jwtModel;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {
    private String jwtToken;
    private String username;
}

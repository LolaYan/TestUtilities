<?php

/* Append modulus 11 check digit to supplied string of digits. */
function GenMOD11( $base_val )
{
   $result = "";
   $weight = array( 2, 3, 4, 5, 6, 7,
                    2, 3, 4, 5, 6, 7,
                    2, 3, 4, 5, 6, 7,
                    2, 3, 4, 5, 6, 7 );

   /* For convenience, reverse the string and work left to right. */
   $reversed_base_val = strrev( $base_val );
   for ( $i = 0, $sum = 0; $i < strlen( $reversed_base_val ); $i++ )
   {
      /* Calculate product and accumulate. */
      $sum += substr( $reversed_base_val, $i, 1 ) * $weight[ $i ];
   }

   /* Determine check digit, and concatenate to base value. */
   $remainder = $sum % 11;
   switch ( $remainder )
   {
   case 0:
      $result = $base_val . 0;
      break;
   case 1:
      $result = "n/a";
      break;
   default:
      $check_digit = 11 - $remainder;
      $result = $base_val . $check_digit;
      break;
   }

   return $result;
}
echo GenMOD11("620874");
?>
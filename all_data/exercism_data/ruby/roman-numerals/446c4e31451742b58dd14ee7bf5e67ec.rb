class Fixnum
    def to_roman#(number_to_convert)
        number_to_convert = self
        result = ""
        numeral_chart = {1000 => "M",
                         900  => "CM",
                         500  => "D",
                         400  => "CD",
                         100  => "C",
                         90   => "XC",
                         50   => "L",
                         40   => "XL",
                         10   => "X",
                         9    => "IX",
                         5    => "V",
                         4    => "IV",
                         1    => "I" }
        numeral_chart.each do |decimal_numeral, roman_numeral|
            while number_to_convert - decimal_numeral >= 0
                number_to_convert -= decimal_numeral 
                result << roman_numeral
            end
        end
        return result
    end
end

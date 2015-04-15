module Roman
  Fixnum.class_eval do

    def to_roman(value = self, output = "")

      roman_numerals = {
          1000 => "M",
           900 => "CM",
           500 => "D",
           400 => "CD",
           100 => "C",
            90 => "XC",
            50 => "L",
            40 => "XL",
            10 => "X",
             9 => "IX",
             5 => "V",
             4 => "IV",
             1 => "I"
      }

      roman_numerals.keys.each { | divisor |
        quotient, remainder = value.divmod(divisor)
        output.concat (roman_numerals[divisor] * quotient).to_s
        return to_roman(remainder, output) if quotient > 0
      }

      return output
    end
  end
end

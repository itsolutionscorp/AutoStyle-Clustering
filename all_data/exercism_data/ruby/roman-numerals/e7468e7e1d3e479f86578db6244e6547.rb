class Fixnum

  ROMAN_NUMERALS = { 
   thousands: ["M"],
   hundreds:  ["C", "CD", "D", "CM"],
   tens:      ["X", "XL", "L", "XC"],
   ones:      ["I", "IV", "V", "IX"]
  }

  def to_roman
    result =  by_digit(self / 1000,       :thousands)
    result << by_digit(self % 1000 / 100, :hundreds)
    result << by_digit(self % 100 / 10,   :tens)
    result << by_digit(self % 10,         :ones)
  end
 
  private

    def by_digit(digit, place)
      result = ""
      symbols = ROMAN_NUMERALS[place]

      if digit > 0 && digit < 10
        if digit < 4
          digit.times { result << symbols[0] }
        elsif digit == 4
          result = symbols[1]
        elsif digit == 5
          result = symbols[2]
        elsif digit < 9
          result << symbols[2]  
          (digit-5).times { result << symbols[0] }
        else
          result = symbols[3]
        end
      end

      result
    end
end

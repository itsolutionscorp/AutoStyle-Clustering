class Fixnum
  def to_roman
    Roman.to_roman(self)
  end
end

class Roman
  ROMAN_NUMERALS = {
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

  def self.to_roman(number)
    current = number
    ROMAN_NUMERALS.keys.reduce("") do |result, numeral|
      quotient, current = current.divmod(numeral)
      result << ROMAN_NUMERALS[numeral] * quotient
    end
  end
end

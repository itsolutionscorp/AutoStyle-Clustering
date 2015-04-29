class Fixnum

  def to_roman
    RomanNumeral.from_integer(self.to_i)
  end

end

class RomanNumeral

  HUNDREDS = ['', 'C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM']
  TENS = ['', 'X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC']
  ONES = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX']

  def self.from_integer(decimal)
    output = ""
    while decimal >= 1000
      output << "M"
      decimal -= 1000
    end

    output << HUNDREDS[decimal/100]
    decimal = decimal % 100

    output << TENS[decimal/10]
    decimal = decimal % 10

    output << ONES[decimal]
  end

end

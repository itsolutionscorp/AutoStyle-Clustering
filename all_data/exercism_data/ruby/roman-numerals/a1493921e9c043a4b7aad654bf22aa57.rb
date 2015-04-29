class Fixnum
  def to_roman
    Roman.to_roman(self)
  end
end

class Roman
  NUMERAL_TO_DECIMAL = {
    'M'  => 1000,
    'CM' => 900,
    'D'  => 500,
    'CD' => 400,
    'C'  => 100,
    'XC' => 90,
    'L'  => 50,
    'XL' => 40,
    'X'  => 10,
    'IX' => 9,
    'V'  => 5,
    'IV' => 4,
    'I'  => 1
  }

  def self.to_roman(decimal_number)
    roman_numerals = ''
    decimal = decimal_number
    NUMERAL_TO_DECIMAL.each do |roman_numeral, decimal_value|
      while (decimal >= decimal_value)
        roman_numerals += roman_numeral
        decimal -= decimal_value
      end
    end
    roman_numerals
  end
end

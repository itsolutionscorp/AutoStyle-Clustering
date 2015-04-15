class Fixnum
  def to_roman
    Roman.to_roman(self)
  end
end

module Roman
  DECIMAL_TO_ROMAN = {
    1000 => 'M',
    900  => 'CM',
    500  => 'D',
    400  => 'CD',
    100  => 'C',
    90   => 'XC',
    50   => 'L',
    40   => 'XL',
    10   => 'X',
    9    => 'IX',
    5    => 'V',
    4    => 'IV',
    1    => 'I'
  }

  def self.to_roman(decimal_number, roman_numerals = '')
    return roman_numerals   if decimal_number.zero?
    
    target_decimal, target_roman = DECIMAL_TO_ROMAN.find do |decimal, roman|
      decimal <= decimal_number
    end
    
    to_roman(decimal_number - target_decimal, roman_numerals + target_roman)
  end
end

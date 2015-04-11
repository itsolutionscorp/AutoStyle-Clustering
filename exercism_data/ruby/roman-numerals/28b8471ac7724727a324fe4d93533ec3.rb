# We open up the Fixnum class to add a way to convert to roman numerals
class Fixnum

  NUMERALS = {
    1 => 'I',
    4 => 'IV',
    5 => 'V',
    9 => 'IX',
    10 => 'X',
    40 => 'XL',
    50 => 'L',
    90 => 'XC',
    100 => 'C',
    400 => 'CD',
    500 => 'D',
    900 => 'CM',
    1000 => 'M'
  }

  def to_roman
    number = self
    roman_numeral = ''
    NUMERALS.keys.reverse.each do |key|
      while number >= key
        roman_numeral << NUMERALS[key] 
        number -= key
      end
    end
    roman_numeral
  end
end

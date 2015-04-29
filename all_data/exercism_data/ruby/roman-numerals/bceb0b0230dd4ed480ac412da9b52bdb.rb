class Fixnum
  def to_roman
    RomanNumeral.get_numeral_for self
  end
end

class RomanNumeral

    NUMERALS = {
      1000 => 'M',
      900 => 'CM',
      500 => 'D',
      400 => 'CD',
      100 => 'C',
      90 => 'XC',
      50 => 'L',
      40 => 'XL',
      10 => 'X',
      9 => 'IX',
      5 => 'V',
      4 => 'IV',
      1 => 'I'
    }

  def self.get_numeral_for number
    roman_number = ''

    NUMERALS.each do |num, roman_num|
      until number < num
        roman_number += roman_num
        number -= num
      end
    end

    roman_number
  end
end

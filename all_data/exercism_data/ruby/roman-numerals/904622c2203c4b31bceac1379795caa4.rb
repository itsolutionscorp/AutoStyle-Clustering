module RomanNumeral
  def to_roman
    roman_numeral = ""
    arabic_numeral = self
    number_list = numeral_map.keys.sort.reverse
    while arabic_numeral >= 1
      current_number = number_list.first
      while number_list.any? && current_number <= arabic_numeral
        roman_numeral << numeral_map[current_number]
        arabic_numeral -= current_number
      end
      number_list.shift
    end
    roman_numeral
  end

  private
  def numeral_map
    {
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
  end

end

class Fixnum
  include RomanNumeral
end

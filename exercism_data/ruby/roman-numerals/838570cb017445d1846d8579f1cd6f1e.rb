class Fixnum
  ARABIC_ROMAN_MAPPING = {1000 => 'M',
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
                          1 => 'I'}

  def to_roman
    roman_result = ""
    remaining_arabic_number = self

    ARABIC_ROMAN_MAPPING.each_pair{|arabic_pair, roman_pair|
      while remaining_arabic_number >= arabic_pair do
        roman_result += roman_pair
        remaining_arabic_number -= arabic_pair
      end
    }
    roman_result
  end
end

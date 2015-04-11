class Integer
  ARABIC_TO_ROMAN = {1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD', 100 => 'C',
              90 => 'XC', 50 => 'L', 40 => 'XL', 10 => 'X',
              9 => 'IX', 8 => 'VIII', 7 => 'VII', 6 => 'VI', 5 => 'V',
              4 => 'IV', 3 => 'III', 2 => 'II', 1 => 'I'}

  def to_roman
    arabic_num = self
    roman_num = ''
    ARABIC_TO_ROMAN.each do |arabic_key, roman_val|
      (arabic_num / arabic_key).times{roman_num += roman_val}
      arabic_num = arabic_num % arabic_key
    end
    roman_num
  end
end

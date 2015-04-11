class Fixnum

  ROMAN_HASH = {1=> 'I', 4=> 'IV', 5=> 'V', 9=> 'IX', 10=> 'X', 40=> 'XL',
  50=> 'L', 90=> 'XC', 100 => 'C', 400=> 'CD', 500=> 'D', 900=> 'CM',
  1000=> 'M'}

  def to_roman
    roman_numeral = ''
    num = self

    while num > 0
      closest_number = ROMAN_HASH.each_key.select {|key| key <= num}.last
      roman_numeral << ROMAN_HASH[closest_number]
      num -= closest_number
    end

    roman_numeral
  end

end

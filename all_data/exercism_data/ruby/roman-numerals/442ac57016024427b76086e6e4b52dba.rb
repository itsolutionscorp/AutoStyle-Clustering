class Fixnum
  ROMAN_MAP = {1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD', 100 => 'C', 90 => 'XC', 50 => 'L', 40 => 'XL', 10 => 'X', 9 => 'IX', 5 => 'V', 4 => 'IV', 1 => 'I'}

  def to_roman
    roman_string = ''
    value = self
    ROMAN_MAP.keys.each do |k|
      if value >= k
        roman_string << ROMAN_MAP[k]
        value -= k
        redo if value > 0
      end
    end
    roman_string
  end
end

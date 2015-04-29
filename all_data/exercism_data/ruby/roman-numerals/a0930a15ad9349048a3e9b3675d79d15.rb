class Integer
  ROMAN_NUMBERS = {
    1000 => 'M', 500 => 'D',
    100 => 'C', 50 => 'L',
    10 => 'X', 5 => 'V',
    1 => 'I'
  }

  def to_roman
    number = self
    roman = ''

    while number > 0
      n = ROMAN_NUMBERS.keys.find { |i| i <= number }

      number -= n
      roman << ROMAN_NUMBERS[n]
    end

    replacements = {
      'IIII' => 'IV', 'XXXX' => 'XL', 'CCCC' => 'CD',
      'VIV' => 'IX', 'LXL' => 'XC', 'DCD' => 'CM'
    }

    replacements.each do |k, v|
      roman.gsub!(k, v)
    end

    roman
  end
end

class Integer

  def to_roman
    remainder = self
    roman_numeral = ''
    while remainder > 0
      largest_base_decimal = base_numerals.keys.select { |n| n <= remainder }.max
      largest_base_roman_numeral = base_numerals[largest_base_decimal]

      roman_numeral << largest_base_roman_numeral
      remainder -= largest_base_decimal
    end

    roman_numeral
  end

  private

    def base_numerals
      { 1 => 'I',
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
        1000 => 'M' }
    end
end

class Integer
  DIGITS = {1000 => {bottom: 'M', middle: nil, top: nil},
            100 => {bottom: 'C', middle: 'D', top: 'M'},
            10 => {bottom: 'X', middle: 'L', top: 'C'},
            1 => {bottom: 'I', middle: 'V', top: 'X'}}

  def to_roman
    roman_numerals = ''
    current_value = self
    [1000, 100, 10, 1].each do |divisor|
      digit = current_value / divisor
      unless digit.zero?
        current_digits = DIGITS[divisor]
        roman_numerals += case digit
          when 1..3 then  current_digits[:bottom] * digit
          when 4 then current_digits[:bottom] + current_digits[:middle]
          when 5 then current_digits[:middle]
          when 6..8 then current_digits[:middle] + current_digits[:bottom] * (digit - 5)
          when 9 then current_digits[:bottom] + current_digits[:top]
          when 10 then current_digits[:top]
        end
      end
      current_value = current_value % divisor
    end
    roman_numerals
  end

end

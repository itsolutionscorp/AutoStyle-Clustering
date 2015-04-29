class Fixnum
  def to_roman
    digits = self.to_s.chars.map(&:to_i).reverse

    digits.each_with_index.reduce('') do |roman_number, (digit, i)|
      next(roman_number) if digit == 0
      roman_digit = Roman.to_roman_digit(digit, i+1)
      roman_number.prepend(roman_digit)
    end
  end
end
class Roman
  @@mapping_decimal_to_roman = {
    1 => { 1 => "I", 5 => "V", 10 => "X" },
    2 => { 1 => "X", 5 => "L", 10 => "C" },
    3 => { 1 => "C", 5 => "D", 10 => "M" },
    4 => { 1 => "M"}
  }

  def self.to_roman_digit(decimal_digit, position)
    roman_digits = @@mapping_decimal_to_roman[position]

    if (decimal_digit + 1) % 5 == 0
      "#{roman_digits[1]}#{roman_digits[decimal_digit + 1]}"
    elsif decimal_digit < 5
      "#{digit_times(decimal_digit, roman_digits[1])}"
    else
      "#{roman_digits[5]}#{digit_times(decimal_digit - 5, roman_digits[1])}"
    end 
  end

  def self.digit_times(quantity, digit)
    quantity.times.map { digit }.join('')
  end
end

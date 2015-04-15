class Fixnum


  def to_roman
    digits = self.to_s.chars.map(&:to_i).reverse

    roman = []
    digits.each_with_index do |digit, i|
      roman << Roman.to_roman_digit(digit, i+1) unless digit == 0
    end
    roman.reverse.join
  end
end
class Roman
  @@mapping_decimal_to_roman = {
    1 => { 1 => "I", 5 => "V", 10 => "X" },
    2 => { 1 => "X", 5 => "L", 10 => "C" },
    3 => { 1 => "C", 5 => "D", 10 => "M" },
    4 => { 1 => "M"}
  }

  def self.to_roman_digit(digit, position)
    mapping = @@mapping_decimal_to_roman[position]

    if digit == 4
      "#{mapping[1]}#{mapping[5]}"
    elsif digit == 9
      "#{mapping[1]}#{mapping[10]}"
    elsif digit < 5
      "#{roman_digit_times(digit, mapping[1])}"
    else
      "#{mapping[5]}#{roman_digit_times(digit - 5, mapping[1])}"
    end 
  end

  def self.roman_digit_times(quantity, roman)
    quantity.times.map { roman }.join('')
  end
end

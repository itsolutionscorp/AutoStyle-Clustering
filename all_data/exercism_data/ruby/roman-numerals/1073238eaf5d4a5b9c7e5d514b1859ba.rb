class Fixnum
  def to_roman
    numbers = to_arabic_array(self)
    roman_collect = Array.new
    numbers.each_with_index { |digit, index| roman_collect << romanize(digit, index) }
    roman_collect.reverse.join
  end

  private
  def to_arabic_array(number)
    number.to_s.chars.reverse.map(&:to_i)
  end

  def romanize(digit, index)
    case index
    when 0
      unit(digit, "I", "V", "X")
    when 1
      unit(digit, "X", "L", "C")
    when 2
      unit(digit, "C", "D", "M")
    else
      "M" * digit * 10**(index -3)
    end
  end

  def unit(digit, one, five, ten)
    case digit
    when 1..3
      one * digit
    when 4
      one + five
    when 5..8
      five + one * (digit %5)
    when 9
      one + ten
    end
  end

end

class Integer
  NUMERALS = ['I', 'V', 'X', 'L', 'C', 'D', 'M']
  
  def to_roman
    digits = to_s.chars.map(&:to_i)
    digits.reverse.each_with_index.map do |digit, place|
      convert_digit(digit, *numerals_for_place(place))
    end.reverse.join
  end

  def numerals_for_place(place)
    NUMERALS[2 * place, 3]
  end

  def convert_digit(digit, one, five = "", ten = "")
    case digit
    when 0
      ""
    when 1..3
      one * digit
    when 4
      one + five
    when 5..8
      five + one * (digit - 5)
    when 9
      one + ten
    end
  end
end

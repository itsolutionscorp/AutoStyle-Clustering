class Fixnum
  ROMANS = [['M', '', ''], ['C', 'D', 'M'], ['X', 'L', 'C'], ['I', 'V', 'X']]

  def to_roman
    digits = [1000, 100, 10, 1].map{ |n| self/n%10 }

    digits.zip(ROMANS).map{ |digit, roman_symbols| roman_digit(digit, *roman_symbols) }.join
  end

  private

  def roman_digit(n, roman_one, roman_five, roman_ten)
    case n
    when 0
      ''
    when 1, 2, 3
      roman_one * n
    when 4
      roman_one + roman_five
    when 5
      roman_five
    when 6, 7, 8
      roman_five + roman_one * (n-5)
    when 9
      roman_one + roman_ten
    end
  end
end

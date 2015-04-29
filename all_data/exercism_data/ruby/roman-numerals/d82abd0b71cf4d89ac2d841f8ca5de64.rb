# Fixnum Extension
class Fixnum
  ROMAN_CONVERTER = [
    %w(I V),
    %w(X L),
    %w(C D),
    %w(M)
  ]

  def to_roman
    return nil if self < 1 && self > 3000

    to_digits.reverse.each_with_index.reduce([]) do |roman, (digit, position)|
      roman << convert_digit_to_roman(digit, position)
    end.reverse.join
  end

  private

  def to_digits
    to_s.chars.map(&:to_i)
  end

  def convert_digit_to_roman(digit, position)
    one_sym, five_sym = ROMAN_CONVERTER[position]
    ten_sym,          = ROMAN_CONVERTER[position + 1]

    case digit
    when 1, 2, 3
      one_sym * digit
    when 4
      one_sym + five_sym
    when 5
      five_sym
    when 6, 7, 8
      (five_sym + one_sym * (digit - 5))
    when 9
      (one_sym + ten_sym) unless ten_sym.nil?
    end
  end
end

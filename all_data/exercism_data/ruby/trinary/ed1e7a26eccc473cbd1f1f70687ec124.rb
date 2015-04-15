class Trinary
  def initialize trinary_string
    @trinary_digits = trinary_string.chars.map &:to_i
  end

  def to_decimal
    sum = 0
    @trinary_digits.reverse.each.with_index{|d,idx|
      sum += d * 3**idx
    }
    sum
  end
end

class Series
  def initialize digit_string
    @digits = digit_string.chars.map &:to_i
  end

  def slices n
    raise(ArgumentError, 'Slice bigger than the number of digits') if n > @digits.count
    @digits.each_cons(n).to_a
  end
end

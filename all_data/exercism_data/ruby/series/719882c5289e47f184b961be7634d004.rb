class Series
  def initialize digit_string
    @digits = digit_string.chars.map &:to_i
  end

  def slices length
    raise ArgumentError if length > @digits.length || length < 0
    @digits.each_cons(length).to_a
  end
end

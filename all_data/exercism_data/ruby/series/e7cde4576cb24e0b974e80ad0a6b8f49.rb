class Series
  attr_reader :digits

  def initialize(digit_string)
    @digits = digit_string.chars.map(&:to_i)
  end

  def slices(n)
    fail ArgumentError if n > digits.size
    digits.each_cons(n).to_a
  end
end

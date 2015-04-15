class Series
  attr_reader :digits

  def initialize(numeric_string)
    @digits = to_digits(numeric_string)
  end

  def slices(length)
    raise ArgumentError if length > digits.length
    digits.each_cons(length).to_a
  end

  private

  def to_digits(s)
    s.chars.map(&:to_i)
  end
end

class Series
  def initialize(string_of_digits)
    @digits = string_of_digits.chars.map(&:to_i)
  end

  def slices(n)
    fail ArgumentError if n > digits.length
    digits.each_cons(n).to_a
  end

  private

  attr_reader :digits
end

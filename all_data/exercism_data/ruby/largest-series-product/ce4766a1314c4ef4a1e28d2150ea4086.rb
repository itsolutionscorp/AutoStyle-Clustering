class Series
  attr_reader :digits

  def initialize(digit_string)
    @digits = digit_string.each_char.map(&:to_i)
  end

  def slices(length)
    raise ArgumentError if length > digits.size
    Array.new(digits.size - length + 1) { |index| digits.slice(index, length) }
  end

  def largest_product(length)
    slices(length).map { |slice| slice.reduce(1, :*) }.max
  end
end

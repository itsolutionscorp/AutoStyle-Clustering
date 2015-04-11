class Series
  attr_reader :digits

  def initialize(numeric_string)
    @digits = to_digits(numeric_string)
  end

  def slices(length)
    raise ArgumentError if length > digits.length
    result = []
    number_of_slices = digits.length - length + 1
    number_of_slices.times do |i|
      result << digits[i, length]
    end
    result
  end

  private

  def to_digits(s)
    s.chars.map(&:to_i)
  end
end

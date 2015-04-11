class Series
  attr_reader :digits

  def initialize(numeric_string)
    @digits = convert_to_digits(numeric_string)
  end

  def slices(length)
    raise ArgumentError if length > digits.length
    result = []
    number_of_slices = digits.length - length + 1
    number_of_slices.times do |idx|
      result << digits[idx, length]
    end
    result
  end

  private

  def convert_to_digits(s)
    s.chars.map(&:to_i)
  end
end

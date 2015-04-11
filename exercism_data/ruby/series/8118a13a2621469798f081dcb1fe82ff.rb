class Series
  def initialize(string_of_digits)
    @string_of_digits = string_of_digits
  end

  def slices(length)
    raise ArgumentError unless string_of_digits.length >= length

    slice_ary = []

    string_of_digits.length.times do |index|
      slice_str = string_of_digits[index...(index + length)]
      result = slice_str.each_char.map(&:to_i)
      break if result.length < length
      slice_ary << result
    end

    slice_ary
  end

  private

  attr_reader :string_of_digits
end

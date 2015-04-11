class Series
  def initialize(string_of_digits)
    @string_of_digits = string_of_digits
  end

  def digits
    @string_of_digits.chars.map(&:to_i)
  end

  def largest_product(slice_length)
    return 1 if slice_length.zero?

    slices(slice_length).map { |slice| slice.inject(:*) }.max
  end

  def slices(slice_length)
    if slice_length > digits.length
      raise ArgumentError, "Slice length (#{slice_length}) can't exceed number of digits (#{digits.length})."
    end

    digits.each_cons(slice_length).to_a
  end
end

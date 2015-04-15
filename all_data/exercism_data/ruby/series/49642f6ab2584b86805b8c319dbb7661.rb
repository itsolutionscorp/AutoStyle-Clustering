class Series
  def initialize string_of_digits
    @digits = string_of_digits.chars.map(&:to_i)
  end

  def slices slice_length
    fail ArgumentError, "Requested slice is larger than string" if @digits.length < slice_length

    @digits.each_cons(slice_length).to_a
  end
end

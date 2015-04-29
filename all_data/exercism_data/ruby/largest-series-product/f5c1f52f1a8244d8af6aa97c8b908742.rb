class Series
  def initialize string_of_digits
    @string_of_digits = string_of_digits
  end

  def digits
    @digits ||= @string_of_digits.chars.map(&:to_i)
  end

  def slices(length)
    digits.each_cons(length).to_a
  end

  def products(length)
    slices(length).map {|digits| digits.inject(&:*) }
  end

  def largest_product length
    fail ArgumentError, "Number of digits requested was larger than number of digits in input" if length > digits.length
    return 1 if length.zero?

    products(length).max
  end
end

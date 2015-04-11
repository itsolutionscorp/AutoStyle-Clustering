class Series
  def initialize string_of_digits
    @digits = string_of_digits.chars.map(&:to_i)
  end

  def slices nr
    fail ArgumentError, "Requested slice is larger than string" if @digits.length < nr

    @digits.each_cons(nr).to_a
  end
end

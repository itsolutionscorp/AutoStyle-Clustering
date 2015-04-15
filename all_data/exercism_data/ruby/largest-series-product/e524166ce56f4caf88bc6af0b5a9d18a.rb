class Series

  attr_reader :digits

  def initialize(string)
    @digits = string.each_char.map(&:to_i)
  end

  # Iterates the given block for each array of consecutive n elements.
  def slices(n)
    digits.each_cons(n).to_a
  end

  def largest_product(n)
    raise ArgumentError if n > digits.size
    sum_slices(n)
  end

  private
  def sum_slices(n)
    return 1 if digits.size == 0
    slices(n).map { |slice| slice.reduce(:*) } .max
  end

end

class Series

  attr_reader :digits
  def initialize(str)
    @digits = str.split(//).map(&:to_i)
  end

  def slices(size)
    digits.each_cons(size).to_a
  end

  def largest_product(size)
    raise ArgumentError if size > digits.size
    return 1 if digits.empty?
    slices(size).map { |slice| slice.reduce(:*) }.max
  end

end

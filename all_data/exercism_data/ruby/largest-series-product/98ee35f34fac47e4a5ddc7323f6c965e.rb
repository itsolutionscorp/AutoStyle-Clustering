class Series
  attr_reader :digits

  def initialize(digits)
    @digits = digits.chars.map(&:to_i)
  end

  def slices(size)
    @digits.each_cons(size).to_a
  end

  def largest_product(size)
    return 1 if size.zero?
    raise ArgumentError if size > @digits.length
    
    slices(size).map { |slice| slice.reduce(:*) }.max
  end
end

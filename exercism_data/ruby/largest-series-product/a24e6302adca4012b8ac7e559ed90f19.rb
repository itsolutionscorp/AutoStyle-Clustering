class Series
  def initialize(numbers)
    @numbers = numbers
  end

  def largest_product(num_of_factors)
    raise ArgumentError if num_of_factors > @numbers.length
    return 1 if num_of_factors == 0
    slices(num_of_factors).map { |x| x.inject(&:*) }.max
  end

  def slices(size)
    digits.each_cons(size).to_a
  end

  def digits
    @numbers.split("").map(&:to_i)
  end
end

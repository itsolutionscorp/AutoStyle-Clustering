class Triplet
  def self.where(min_factor: 3, max_factor: 5, sum: nil)
    [*min_factor..max_factor].combination(3)
      .select { |a, b, c| a**2 + b**2 == c**2 }
      .reject { |a, b, c| sum && a + b + c != sum }
      .map    { |a, b, c| new(a, b, c) }
  end

  attr_reader :sum, :product

  def initialize(a, b, c)
    @a, @b, @c = [a, b, c].sort
    @sum = a + b + c
    @product = a * b * c
  end

  def pythagorean?
    @a**2 + @b**2 == @c**2
  end
end

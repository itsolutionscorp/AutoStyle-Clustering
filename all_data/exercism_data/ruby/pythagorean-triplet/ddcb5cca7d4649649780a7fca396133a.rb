class Triplet
  class << self
    def where(min_factor: 1, max_factor: 100, sum: nil)
      combinations = (min_factor..max_factor).to_a.combination(3).to_a
      combinations.select! { |a, b, c| a + b + c == sum } if sum

      combinations.each_with_object([]) do |(a, b, c), result|
        triplet = new(a, b, c)
        result << triplet if triplet.pythagorean?
      end
    end
  end

  attr_reader :a, :b, :c

  def initialize(a, b, c)
    @a, @b, @c = a, b, c
  end

  def sum
    a + b + c
  end

  def product
    a * b * c
  end

  def pythagorean?
    a ** 2 + b ** 2 == c ** 2
  end
end

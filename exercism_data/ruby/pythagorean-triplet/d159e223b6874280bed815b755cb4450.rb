class Triplet
  def self.where(max_factor:, min_factor: 3, sum: nil)
    numbers = (min_factor..max_factor).to_a.combination(3)

    numbers.each_with_object([]) do |(a, b, c), triples|
      triplet = new(a, b, c)

      if triplet.pythagorean? && (sum.nil? || triplet.sum == sum)
        triples << triplet
      end
    end
  end

  def initialize(a, b, c)
    @numbers = [a, b, c]
  end

  def sum
    numbers.reduce(:+)
  end

  def product
    numbers.reduce(:*)
  end

  def pythagorean?
    numbers[0]**2 + numbers[1]**2 == numbers[2]**2
  end

  private

  attr_reader :numbers
end

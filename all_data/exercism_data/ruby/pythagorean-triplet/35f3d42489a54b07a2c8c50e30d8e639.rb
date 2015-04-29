class Triplet
  def self.where(min_factor: 1, max_factor: 1, sum: nil)
    triplets = []

    # Optimized for simplicity, but also see e.g.
    # http://stackoverflow.com/q/575117/6962
    (min_factor...max_factor).each do |a|
      (a...max_factor).each do |b|
        c = Math.sqrt(a**2 + b**2).round
        triplet = new(a, b, c)
        triplets << triplet if triplet.pythagorean?
      end
    end

    triplets.select! { |t| t.sum == sum } if sum

    triplets
  end

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
    a**2 + b**2 == c**2
  end

  private

  attr_reader :a, :b, :c
end

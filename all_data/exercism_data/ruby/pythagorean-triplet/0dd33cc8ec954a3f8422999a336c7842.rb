class Triplet
  def self.where(min_factor: 1, max_factor: 1, sum: nil)
    triplets = []

    # Optimized for simplicity, but also see e.g.
    # http://stackoverflow.com/q/575117/6962
    min_factor.upto(max_factor) do |a|
      a.upto(max_factor) do |b|
        b.upto(max_factor) do |c|
          triplets << new(a, b, c)
        end
      end
    end

    triplets.select! { |t| t.sum == sum } if sum
    triplets.select(&:pythagorean?)
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

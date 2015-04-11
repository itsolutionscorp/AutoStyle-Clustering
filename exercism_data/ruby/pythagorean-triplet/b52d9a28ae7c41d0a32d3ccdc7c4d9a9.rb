class Triplet
  def self.where(min_factor: 1, max_factor: 1, sum: nil)
    triplets = []

    # If further optimization is needed: http://stackoverflow.com/q/575117/6962
    (min_factor..max_factor).each do |a|
      (a.succ..max_factor).each do |b|
        (b.succ..max_factor).each do |c|
          triplet = new(a, b, c)
          break triplets << triplet if triplet.pythagorean?
        end
      end
    end

    if sum
      triplets.select { |t| t.sum == sum }
    else
      triplets
    end
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

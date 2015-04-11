class Triplet
  attr_reader :sides
  def initialize(a, b, c)
    @sides = [a, b, c].sort
  end

  def sum
    @sides.reduce(:+)
  end

  def product
    @sides.reduce(:*)
  end

  def pythagorean?
    @sides[0] ** 2 + @sides[1] ** 2 == @sides[2] ** 2
  end

  def satisfy_sum?(target_sum)
    target_sum.nil? || sum == target_sum
  end

  def self.where(min_factor: 1, sum: nil, max_factor:)
    (min_factor..max_factor).each.with_object([]) do |a, triplets|
      b = a
      while (c = Math.sqrt(a ** 2 + b ** 2)) <= max_factor
        if whole_number?(c)
          triplet = Triplet.new(a, b, c.truncate)
          triplets << triplet if triplet.satisfy_sum?(sum)
        end
        b += 1
      end
    end
  end

  private

  def self.whole_number?(n)
    n == n.truncate
  end
end

class Triplet
  def initialize(a, b, c)
    @a, @b, @c = a, b, c
  end

  def sum
    @a + @b + @c
  end

  def product
    @a * @b * @c
  end

  def pythagorean?
    @a ** 2 + @b ** 2 == @c ** 2
  end

  def self.where(min_factor: 1, max_factor:  10, sum: nil)
    (min_factor..max_factor).each.with_object([]) do |x, result|
      (x..max_factor).each do |y|
        (y..max_factor).each do |z|
          triplet = Triplet.new(x, y, z)
          result << triplet if triplet.pythagorean? && (!sum || sum == triplet.sum)
        end
      end
    end
  end

end

class Triplet
  def initialize(a, b, c)
    @a, @b, @c = a, b, c
  end

  def self.where(min_factor: 1, max_factor: 1, sum: nil)
    triplets(min_factor, max_factor).select do |triplet|
     triplet.pythagorean? && (!sum || triplet.sum == sum )
   end.compact
  end

  def sum
    sides.inject :+
  end

  def product
    sides.inject :*
  end

  def pythagorean?
    self if @a ** 2 + @b ** 2 == @c ** 2
  end

private

  def sides
    [@a, @b, @c]
  end

  def self.triplets(from, to)
    [*from..to].permutation(2).each_with_object([]) do |(a, b), array| 
      hypo = hypotenuse a, b if a < b
      array << new(a, b, hypo) if hypo && hypo <= to
    end
  end

  def self.hypotenuse(a, b)
    hypo = Math.sqrt(a ** 2 + b ** 2)
    hypo.to_i if hypo.floor == hypo
  end

end

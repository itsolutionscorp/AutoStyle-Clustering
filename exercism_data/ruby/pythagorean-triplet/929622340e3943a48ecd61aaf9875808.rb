class Array
  def product
    reduce(1) { |a, e| a * e }
  end

  def sum
    reduce(0) { |a, e| a + e }
  end
end

class Triplet
  attr_reader :a, :b, :c

  def initialize a, b, c
    @a, @b, @c = a, b, c
  end

  def sum
    [a, b, c].sum
  end

  def product
    [a, b, c].product
  end

  def pythagorean?
    a**2 + b**2 == c**2
  end

  def self.where arg = {}
    max = arg[:max_factor]
    min = arg[:min_factor] || 1
    s = arg[:sum]
    combis = [*min..max].combination(3).to_a
    combis.keep_if { |a, b, c| [a, b, c].sum == s } if s
    combis.select { |a, b, c| Triplet.new(a, b, c).pythagorean? }
  end
end

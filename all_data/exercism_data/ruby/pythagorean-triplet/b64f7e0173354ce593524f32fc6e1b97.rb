class Triplets
  def initialize(max, min, sum = nil)
    @max, @min, @sum = max, min, sum
  end

  def to_a
    [].tap do |out|
      each_triplet do |triplet|
        out << triplet if valid?(triplet)
      end
    end
  end

  def each_triplet
    (@min..@max).to_a.combination(3).each do |a, b, c|
      yield Triplet.new(a, b, c)
    end
  end

  def valid?(triplet)
    triplet.pythagorean? && (@sum.nil? || triplet.sum == @sum)
  end
end

class Triplet
  def initialize(a, b, c)
    @numbers = [a, b, c]
  end

  def sum
    @numbers.inject(0, :+)
  end

  def product
    @numbers.inject(1, :*)
  end

  def pythagorean?
    a, b, c = @numbers.sort
    (a**2 + b**2) == c**2
  end

  def self.where(max_factor: 1, min_factor: 1, sum: nil)
    Triplets.new(max_factor, min_factor, sum).to_a
  end
end

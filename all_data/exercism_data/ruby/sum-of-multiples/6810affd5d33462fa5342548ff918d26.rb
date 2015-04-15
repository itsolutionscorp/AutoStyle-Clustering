class SumOfMultiples
  @@default_factors = [3, 5]

  def initialize(*args)
    @factors = (@@default_factors if args.empty?) || args
  end

  def to(n)
    SumOfMultiples.to(n, @factors)
  end

  def self.to(n, factors = @@default_factors)
    (0...n).select { |num| factors.any? { |factor| num % factor == 0 } }.reduce(&:+)
  end
end

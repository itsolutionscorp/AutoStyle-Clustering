class SumOfMultiples
  @multiples = [3, 5]

  def initialize(*mults)
    @multiples = mults.sort
  end

  def self.to(limit)
    new(*@multiples).to(limit)
  end

  def to(limit)
    (0...limit).inject([]) { |a, e| a << (@multiples.map { |x| e % x == 0 }.uniq.include?(true) ? e : nil) }
      .compact
      .reduce(:+)
  end
end

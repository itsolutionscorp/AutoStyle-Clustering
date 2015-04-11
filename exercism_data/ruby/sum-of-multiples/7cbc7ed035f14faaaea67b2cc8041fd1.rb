class SumOfMultiples

  FACTORS = [3,5]

  def initialize(*factors)
    @factors = factors
  end

  def to(n)
    self.class.sum(n, @factors)
  end

  def self.to(n)
    sum(n, FACTORS)
  end

  def self.multiples(n, m)
    (0...n).map { |e| e if e%m == 0 }.compact
  end

  def self.sum(n, factors)
    factors.map {|x| multiples(n, x) }.flatten.uniq.reduce(:+)
  end

end

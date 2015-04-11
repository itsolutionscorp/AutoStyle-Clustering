class SumOfMultiples

  def initialize(*factors)
    @factors = factors
  end

  def to(limit)
    SumOfMultiples.sum_of_multiples(limit, @factors)
  end

  def self.to(limit)
    sum_of_multiples(limit)
  end

  private

  def self.sum_of_multiples(limit, factors = [3, 5])
    (1...limit).select { |n| factors.any? { |factor| n % factor == 0 } }.reduce(&:+) || 0
  end
end

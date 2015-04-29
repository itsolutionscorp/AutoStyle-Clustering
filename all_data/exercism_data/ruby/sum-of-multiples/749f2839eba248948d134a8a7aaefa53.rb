class SumOfMultiples
  DEFAULT_FACTORS = [3, 5]

  def initialize(*factors)
    @factors = factors
  end

  def self.to(limit)
    new(*DEFAULT_FACTORS).to(limit)
  end

  def to(limit)
    (1...limit).inject(0) do |sum, number|
      sum + (@factors.any? { |factor| number % factor == 0 } ? number : 0)
    end
  end
end

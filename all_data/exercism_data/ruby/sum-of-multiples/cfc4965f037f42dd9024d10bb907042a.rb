class SumOfMultiples
  def self.to(limit)
    self.new(3, 5).to(limit)
  end

  def initialize(*factors)
    @factors = factors
  end

  def to(limit)
    multiples = (1...limit).select do |n|
      @factors.any? { |f| n % f == 0 }
    end
    multiples.inject(0, :+)
  end
end

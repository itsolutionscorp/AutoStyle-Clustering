class SumOfMultiples
  attr_reader :factors
  def initialize(*args)
    @factors = *args
  end

  def self.to(num, factors=[3, 5])

    (1...num).to_a.select do |nu| 
      factors.any?{ |factor| nu % factor == 0 }
    end.inject(:+) || 0
  end

  def to(num)
    self.class.to(num, @factors)
  end
end

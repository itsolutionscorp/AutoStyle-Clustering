class SumOfMultiples
  attr_reader :mults

  def initialize(*mults)
    @mults = mults
  end

  def self.to(limit)
    sum = 0
    0.upto(limit-1) { |i| sum += i if ((i % 3 == 0) || (i % 5 == 0)) }
    sum
  end

  def to(limit)
    sum = 0
    (1...limit).each do |i|
      sum += i if multiple?(i)
    end
    sum
  end

  private 

  def multiple?(i)
    mults.any? do |mult|
      i % mult == 0
    end
  end

end

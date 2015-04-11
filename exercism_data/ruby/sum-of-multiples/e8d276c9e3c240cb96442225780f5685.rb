class SumOfMultiples
  attr_accessor :multiples

  def initialize *multiples
    self.multiples = multiples
  end

  def to limit
    return 0 if limit < multiples.min
    (1...limit).select do |num|
      multiples.any? { |mult| num % mult == 0 }
    end.inject(:+)
  end

  def self.to limit
    new(3,5).to limit
  end
end

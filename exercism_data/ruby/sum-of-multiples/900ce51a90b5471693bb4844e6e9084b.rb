class SumOfMultiples
  def self.to(stop)
    new(3, 5).to(stop)
  end

  def initialize(*multipliers)
    @multipliers = multipliers
  end

  def to(stop)
    @multipliers.reduce([]) do |multiples, multiplier|
      # Only add each multiples common to several multipliers once.
      multiples | Array(multiplier.step by: multiplier, to: stop - 1)
    end.reduce(0, :+)
  end
end

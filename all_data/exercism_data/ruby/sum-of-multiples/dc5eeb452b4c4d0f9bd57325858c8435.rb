class SumOfMultiples
  def initialize(*multipliers)
    multipliers = [3,5] if multipliers.empty?
    @multipliers = multipliers
  end

  def to(upper_bound)
    @multipliers.inject([]) do |multiples, multiplier|
      multiples + (0...upper_bound).step(multiplier).to_a
    end.uniq.inject(:+).to_i
  end

  def self.to(upper_bound)
    self.new.to(upper_bound)
  end
end

class SumOfMultiples
  def initialize(*multiples)
    @multiples = multiples
  end

  def self.to(max)
    new(3, 5).to(max)
  end

  def to(max)
    @multiples.reduce([0]) do |multiples, num|
      multiples | (num...max).step(num).to_a
    end.reduce(:+)
  end
end

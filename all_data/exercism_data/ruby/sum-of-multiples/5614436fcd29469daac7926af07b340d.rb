class SumOfMultiples

  def initialize(*multiples)
    @multiples = multiples
  end

  def self.to(max)
    new(3, 5).to(max)
  end

  def to(max)
    @multiples.map { |multiple| (1...max).select { |num| num % multiple == 0 } }.flatten.uniq.reduce(0, :+)
  end
end

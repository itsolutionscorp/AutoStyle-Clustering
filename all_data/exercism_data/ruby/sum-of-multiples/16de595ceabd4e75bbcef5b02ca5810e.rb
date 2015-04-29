class SumOfMultiples
  def initialize *multiples
    @multiples = multiples
  end

  def self.to(n)
    new(3,5).to(n)
  end

  def to(n)
    (0...n).select { |num| @multiples.any? { |multiple| num % multiple == 0 } }.reduce(:+)
  end
end

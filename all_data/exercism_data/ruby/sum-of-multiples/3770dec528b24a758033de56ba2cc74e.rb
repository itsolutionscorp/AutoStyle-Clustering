class SumOfMultiples
  def initialize *multiples
    @multiples = multiples
  end

  def self.to(n)
   (0...n).select { |num| num % 3 == 0 || num % 5 == 0 }.reduce(:+)
  end

  def to(n)
    (0...n).select { |num| @multiples.any? { |multiple| num % multiple == 0 } }.reduce(:+)
  end
end

class SumOfMultiples
  def self.to(max)
    new(3, 5).to(max)
  end

  def initialize(*multiples)
    @multiples = multiples
  end

  def to(max)
    (1...max).reduce(0) { |sum, num| sum + (multiple?(num) ? num : 0) }
  end

  private

  def multiple?(num)
    @multiples.any? { |multiple| num % multiple == 0 }
  end
end

class SumOfMultiples
  def self.to limit
    new(3, 5).to limit
  end

  def initialize *multiples
    @multiples = multiples
  end

  def to limit
    (1...limit).reduce(0) do |sum, i|
      sum += i if multiple?(i)
      sum
    end
  end

  private
  def multiple? i
    multiples.any? do |multiple|
      i % multiple == 0
    end
  end

  attr_reader :multiples
end

class SumOfMultiples
  def self.to(max)
    new(3, 5).to(max)
  end

  def initialize(*multiples)
    @multiples = multiples
  end

  def to(max)
    (1...max).reduce(0) do |sum, num|
      sum += num if multiples.any? { |multiple| num % multiple == 0 }
      sum
    end
  end

  private

  attr_reader :multiples
end

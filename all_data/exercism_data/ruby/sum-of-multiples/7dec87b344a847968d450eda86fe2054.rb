class SumOfMultiples
  attr_reader :multiples

  def self.to num
    new(3,5).to(num)
  end

  def initialize *args
    @multiples = args
  end

  def to num
    (1...num).select { |n| multiple?(n) }.reduce(0, :+)
  end

  private
  def multiple? num
    @multiples.any? { |multiple| (num % multiple).zero? }
  end
end

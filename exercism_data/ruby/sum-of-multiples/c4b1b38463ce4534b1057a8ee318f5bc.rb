class SumOfMultiples
  def initialize(*multiples)
    @multiples = multiples
  end

  def to(num)
    num.times.select { |v| @multiples.any? { |n| v % n == 0 } }.reduce(:+)
  end

  def self.to(num)
    new(3, 5).to(num)
  end
end

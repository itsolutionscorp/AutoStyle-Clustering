class SumOfMultiples
  attr_reader :multiples

  def initialize(*multiples)
    @multiples = multiples.empty? ? [3, 5] : multiples
  end

  def self.to(limit)
    new.to(limit)
  end

  def to(limit)
    (0...limit).map { |n| multiple?(n) ? n : 0 }.reduce(:+)
  end

  private

  def multiple?(n)
    multiples.any? { |m| n % m == 0 }
  end
end

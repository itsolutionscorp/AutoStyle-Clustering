class SumOfMultiples
  def initialize(*multiples)
    @multiples = multiples
  end

  def to(n)
    (0...n).to_a.select{ |x| multiple? x }.inject(:+)
  end

  def self.to(n)
    SumOfMultiples.new(3, 5).to(n)
  end

  private

  def multiple?(x)
    @multiples.any? { |m| x % m == 0 }
  end
end

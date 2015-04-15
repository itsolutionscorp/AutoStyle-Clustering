class SumOfMultiples
  def initialize(*n)
    @n = n
  end

  def to(n)
    (3...n).to_a.select do |x|
      @n.any? { |y| (x % y).zero? }
    end.inject(:+)
  end

  def self.to(n)
    sum = (3...n).to_a.select do |x|
      (x % 3).zero? || (x % 5).zero?
    end.inject(:+)

    sum ? sum : 0
  end
end

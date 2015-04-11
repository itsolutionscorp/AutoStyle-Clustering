class Integer
  def multiple_of?(*factors)
    factors.flatten.map { |x| return true if self%x == 0 }
    false
  end
end

class SumOfMultiples
  def self.to(n)
    return 0 if n < 3
    multiples(n).inject(:+)
  end

  def initialize(*factors)
    @factors = factors
  end

  def to(n)
    self.class.multiples(n, @factors).inject(:+)
  end

  private
  def self.multiples(n, *factors)
    mult = []
    factors = [3, 5] if factors.empty?
    (1..n-1).each { |x| mult << x if x.multiple_of?(factors) }
    mult
  end
end

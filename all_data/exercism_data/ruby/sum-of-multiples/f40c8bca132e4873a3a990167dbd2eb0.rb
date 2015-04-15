class SumOfMultiples

  def initialize(*multiples)
    @multiples = *multiples
  end

  def self.to(n)
    self.sum_of_multiples(n, [3, 5])
  end

  def to(n)
    self.class.sum_of_multiples(n, @multiples)
  end

  def self.sum_of_multiples(n, multiples)
    multiples(n, multiples).inject(:+)
  end

  def self.multiples(n, multiples)
    (0..n-1).select { |x| multiple?(x, multiples) }
  end

  def self.multiple?(x, multiples)
    multiples.any? { |m| x % m == 0 }
  end

end

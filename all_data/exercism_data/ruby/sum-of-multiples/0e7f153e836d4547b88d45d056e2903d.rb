class SumOfMultiples
  def initialize (*args)
    @multiples = args
  end

  def self.to(n)
    sum_the_multiples(n, [3, 5])
  end

  def to(n)
    sum_the_multiples(n, @multiples)
  end

  def self.sum_the_multiples(n, array)
    the_sum = 0
    1.upto(n - 1) do |x|
      array.any? {|m| the_sum += x if x % m == 0}
    end
    the_sum
  end

  def sum_the_multiples(n, array)
    the_sum = 0
    1.upto(n - 1) do |x|
      array.any? {|m| the_sum += x if x % m == 0}
    end
    the_sum
  end

end

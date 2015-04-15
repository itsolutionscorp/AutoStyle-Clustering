module Divisible
  def divisible_by_any? nums
    return nums.each.detect{|n| self%n==0} || false
  end
end
Fixnum.send :include, Divisible

class SumOfMultiples
  @@primes = [3, 5]

  def initialize *primes
    @primes = primes
  end

  def to num
    SumOfMultiples.to num, @primes
  end

  def self.to(num, primes=@@primes)
    (3...num).each_with_object([0]) do |n,memo|
      memo << n if n.divisible_by_any? primes
    end.reduce :+ 
  end
end

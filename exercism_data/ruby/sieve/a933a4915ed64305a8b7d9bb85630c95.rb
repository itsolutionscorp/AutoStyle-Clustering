class Sieve
  attr_accessor :n
  def initialize n
    @n = n
  end
  def prime? num
    i = 2
    while i < num
      return false if num % i == 0
      i += 1
    end
      true
  end
  def primes
    primes = []
    2.upto(n) do |i|
      primes << i if prime?(i)
    end
    primes
  end
end

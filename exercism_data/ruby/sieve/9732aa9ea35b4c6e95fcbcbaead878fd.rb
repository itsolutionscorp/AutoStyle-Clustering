require 'set'

class Sieve
  def initialize(number)
    @n = number
  end

  def primes
    sieve @n
  end

  private
  def sieve(n)
    primes = Set.new
    integers = Set.new (2..n)
    until integers.empty?
      prime = integers.min
      #integers.delete prime
      integers = integers - Set.new([prime])
      integers = integers - integers.select {|x| x % prime == 0}
      primes = primes.union(Set.new([prime]))
    end
    return primes.to_a
  end
end

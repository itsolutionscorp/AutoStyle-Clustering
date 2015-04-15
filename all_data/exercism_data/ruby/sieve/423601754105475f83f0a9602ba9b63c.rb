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
      primes += Set.new([integers.min])
      integers -= Set.new (integers.min..integers.max).step(integers.min)
    end
    return primes.to_a
  end
end

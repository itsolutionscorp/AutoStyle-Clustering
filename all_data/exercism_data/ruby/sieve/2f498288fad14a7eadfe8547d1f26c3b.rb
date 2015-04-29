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
    integers = Set.new (2..n)
    Set.new.tap do |primes|
      until integers.empty?
	    primes << integers.min
	    integers.subtract Set.new (integers.min..integers.max).step(integers.min)
      end
    end.to_a
  end
end

require 'prime'

class Prime
  def self.nth(n)
    PrimeFinder.new(n).find_prime
  end
end

class PrimeFinder
  def initialize(n)
    @n = n
    valid_argument?
  end

  def valid_argument?
    raise(ArgumentError, "number must be an integer greater than zero") unless valid_number?
  end

  def valid_number?
    @n == @n.to_i && @n > 0
  end

  def find_prime
    primes = 0
    number = 1
    while primes < @n
      number += 1
      primes += 1 if number.prime?
    end
    number
  end
end

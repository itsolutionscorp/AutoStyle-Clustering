require 'prime'

class Sieve
  def initialize(n)
    @n = n
  end

  def primes
    Prime.each(@n).to_a
  end
end

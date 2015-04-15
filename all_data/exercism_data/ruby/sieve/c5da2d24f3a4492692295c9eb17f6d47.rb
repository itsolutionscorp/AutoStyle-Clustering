require 'prime'
class Sieve
  def initialize(n)
    @number = n
  end
  def primes
    primes = []
    Prime.each(@number) { |prime| primes.push(prime) }
    primes
  end
end

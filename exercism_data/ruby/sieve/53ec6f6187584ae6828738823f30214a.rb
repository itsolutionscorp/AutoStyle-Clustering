require 'prime'
class Sieve
  attr_reader :upper_bound

  def initialize(upper_bound)
    @upper_bound = upper_bound
  end

  def primes
    @primes ||= generate
  end

  private
  def generate
    @primes = []

    # Prime.each defaults to using the Sieve of Eratosthenes
    Prime.each(@upper_bound) { |x| @primes << x }
  end
end

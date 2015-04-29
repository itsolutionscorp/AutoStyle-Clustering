require 'prime'

class Prime
  def nth(number_of_primes)
    raise ArgumentError if number_of_primes == 0
    Prime.first(number_of_primes).last
  end
end

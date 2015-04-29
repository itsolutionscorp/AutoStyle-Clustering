require 'prime'

class Prime
  def self.nth(list)
    raise ArgumentError if list == 0
    array_of_primes = Prime.take(list)
    return array_of_primes[list-1]
  end
end

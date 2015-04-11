require 'prime'

class Prime
  
  def self.nth(n)
    raise ArgumentError if n <= 0
    primes = []
    number = 0
    until primes.size == n do
      primes << number if number.prime?
      number += 1
    end
    primes.max
  end

end

require 'prime'

class Prime
  def self.nth n
    raise ArgumentError if n < 1
    primes = 0
    i = 0
    while primes < n
      i += 1
      primes += 1 if Prime.prime? i
    end
    i
  end
end

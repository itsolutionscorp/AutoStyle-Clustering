require 'Prime'

class Prime
  def self.nth(n)
    raise ArgumentError if n == 0
    primes = Prime.first(n)
    prime_at_nth  = primes.last
  end
end

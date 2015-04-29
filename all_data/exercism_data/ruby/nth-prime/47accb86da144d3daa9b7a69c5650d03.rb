require 'Prime'
class Prime

  def self.nth(n)
    raise ArgumentError if n == 0
    primes = []
    count = 0
    x = 2
    until primes.size == n
        primes << x if x.prime?
        x += 1
    end
    primes.last
  end

end

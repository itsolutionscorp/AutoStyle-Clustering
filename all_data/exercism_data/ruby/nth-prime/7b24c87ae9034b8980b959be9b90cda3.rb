require 'Prime'

class Prime
  def self.nth(n)
    if n > 0
      primes = []
      Prime.each(105_000) do |prime|
        primes << prime
      end
      primes[n - 1]
    else
      raise ArgumentError
    end
  end
end

require 'byebug'
module Prime
  def self.nth(n)
    raise ArgumentError unless n > 0

    primes = [2]
    current = 3

    until primes.length == n
      if primes.all? { |prime| current % prime > 0 }
        primes << current
      end

      current += 1
    end

    primes.last

  end
end

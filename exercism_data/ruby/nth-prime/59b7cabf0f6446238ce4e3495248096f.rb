# Class to find nth prime.
class Prime
  class << self
    attr_accessor :primes

    private

    def find_nth(n)
      primes ||= [2, 3]
      start = primes.last

      while primes.length < n
        start += 2 # No need to check even values.
        primes << start if prime? start
      end

      primes[n - 1]
    end

    def prime?(num)
      !primes.find { |i| num % i == 0 }
    end
  end

  def self.nth(n)
    raise ArgumentError unless n > 0

    find_nth(n)
  end
end

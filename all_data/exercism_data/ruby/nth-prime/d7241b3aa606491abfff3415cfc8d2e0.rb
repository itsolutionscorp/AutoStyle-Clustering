require 'singleton'

# Inspired by
# http://en.wikibooks.org/wiki/Efficient_Prime_Number_Generating_Algorithms#PGsimple3

class Prime
  include Singleton

  def initialize
    @primes = [2, 3]
  end

  def self.nth(n)
    instance.nth(n)
  end

  def nth(n)
    fail ArgumentError unless n > 0
    next_prime while @primes.count < n
    @primes[n - 1]
  end

  def next_prime
    candidate = @primes.last
    loop do
      candidate += 2
      test = true
      candidate_root = Math.sqrt(candidate)
      @primes.each do |p|
        break if p > candidate_root
        if candidate % p == 0
          test = false
          break
        end
      end
      if test
        @primes << candidate
        return candidate
      end
    end
  end
end

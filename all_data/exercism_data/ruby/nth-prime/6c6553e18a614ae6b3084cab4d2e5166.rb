require "singleton"

class Prime
  include Singleton

  def self.nth(nth)
    self.instance.nth(nth)
  end

  def initialize
    @primes = [2]
  end

  def nth(nth)
    raise ArgumentError if nth < 1
    index = nth - 1
    candidate = @primes.last + 1 | 1
    until @primes[index]
      @primes << candidate if prime?(candidate)
      candidate += 2
    end
    @primes[index]
  end

  private

  def prime?(number)
    limit = Math.sqrt(number)
    @primes.each do |p|
      break if p > limit
      return false if (number % p).zero?
    end
    true
  end
end

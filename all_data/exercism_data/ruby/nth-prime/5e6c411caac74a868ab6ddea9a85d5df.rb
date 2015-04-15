#!/usr/bin/env ruby

#
module Prime
  # This needs atleast 2 primes for #more_primes to work
  @primes = [2, 3]
  @max_checked = @primes.last + 1

  def self.nth(n)
    fail ArgumentError if n == 0

    n -= 1 # my primes are zero-indexed
    more_primes until n < @primes.length
    @primes[n]
  end

  def self.more_primes
    # Create an array of possible prime numbers (immediately filter out evens)
    nums = (@max_checked..@max_checked * 2).map { |x| x.odd? ? x : nil }.to_a

    # Our limit is sqrt(p) < max number to sieve on
    upper_limit = @primes.index { |p| p * p > @max_checked * 2 }

    # For those primes, mark as nil their multiples.
    @primes[1..upper_limit].each do |p|
      start_idx = -@max_checked % p # index of the first multiple

      (start_idx..nums.size).step(p) { |x| nums[x] = nil }
    end

    @max_checked *= 2
    @primes += nums.compact
  end
end

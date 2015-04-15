class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    nums = (2..@limit).to_a
    primes = []
    until nums.empty?
      primes << nums.shift
      nums.reject! { |n| n % primes.last == 0 }
    end
    primes
  end
end

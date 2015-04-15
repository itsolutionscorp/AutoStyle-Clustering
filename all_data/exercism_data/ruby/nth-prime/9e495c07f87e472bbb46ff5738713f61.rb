module Prime
  def self.nth(n)
    raise ArgumentError if n < 1
    nums = (2..105000).to_a
    primes = []
    until nums.empty?
      primes << nums.shift
      nums.reject! { |x| x % primes.last == 0 }
    end
    primes[n - 1]
  end
end

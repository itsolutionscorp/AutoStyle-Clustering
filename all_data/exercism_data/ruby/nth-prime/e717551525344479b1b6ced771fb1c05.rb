class Prime
  def self.nth(n)
    raise ArgumentError if n < 1
    primes = [2]
    prime_candidate = 3
    while primes.count <= n - 1
      primes << prime_candidate if prime? prime_candidate
      prime_candidate += 2
    end
    primes.last
  end

  private

    def self.prime?(prime_candidate)
      return true if prime_candidate == 2
      range = (3..Math.sqrt(prime_candidate).ceil).step(2)
      range.none? { |divisor| prime_candidate % divisor == 0 }
    end
end

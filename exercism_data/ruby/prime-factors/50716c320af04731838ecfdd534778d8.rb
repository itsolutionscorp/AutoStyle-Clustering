class PrimeFactors
  def self.for(n, start = 2)
    return [] if n == 1

    first_prime_factor = (start..n).find { |prime| n % prime == 0 }
    [first_prime_factor] + self.for(n / first_prime_factor, first_prime_factor)
  end
end

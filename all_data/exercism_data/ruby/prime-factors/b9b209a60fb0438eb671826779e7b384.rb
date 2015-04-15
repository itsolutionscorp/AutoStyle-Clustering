require "prime"

# Obvious solution, using the stdlib Prime module
module PrimeFactors
  def self.for(n)
    n.prime_division.flat_map { |div, count| [div] * count }
  end
end

# Okay, okay, let's try some DIY.
# I'll still use Prime to generate prime numbers. Other
# problems have already dealt with generating primes, so
# "been there, done that" - would rather focus on the
# factorization.

module PrimeFactors
  def self.for(n)
    factors = []
    while n > 1
      factor, n = self.first_prime_factor(n)
      factors << factor
    end
    factors
  end

  def self.first_prime_factor(n)
    return [n, 1] if n.prime?
    limit = Math.sqrt(n)
    Prime.each do |p|
      break if p > limit
      result, remainder = n.divmod(p)
      return [p, result] if remainder.zero?
    end
    raise StandardError, "Indivisble #{n}"
  end
end

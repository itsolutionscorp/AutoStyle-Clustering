class Prime
  class << self
    def nth(n)
      raise ArgumentError if n < 1
      primes = generate_primes(1_000_000)
      primes[n - 1]
    end

    def generate_primes(limit)
      candidates = Array.new(limit + 1) { true }
      sqrt_of_limit = limit ** 0.5
      divisor = 2
      while divisor <= sqrt_of_limit
        cross_out_multiples(candidates, divisor)
        divisor += 1
      end
      booleans_to_integers(candidates)
    end

    def cross_out_multiples(candidates, n)
      if candidates[n]
        i = n * 2
        while i < candidates.length
          candidates[i] = false
          i += n
        end
      end
    end

    def booleans_to_integers(booleans)
      integers = []
      booleans.each.with_index do |boolean, i|
        integers << i if boolean
      end
      integers[2..-1]
    end
  end
end

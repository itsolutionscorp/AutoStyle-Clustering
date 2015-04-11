module PrimeFactors

  def self.prime_stream
    Enumerator.new do |enum|
      enum.yield 2
      primes = []
      (2..Float::INFINITY).lazy.map{ |i| 2 * i - 1 }.each do |candidate|
        prime_candidate = true
        primes.each_with_index do |(prime, multiple), i|
          if multiple == candidate
            primes[i] = [prime, multiple + 2 * prime]
            prime_candidate = false
          end
        end
        if prime_candidate
          enum.yield candidate
          primes << [candidate, 3 * candidate]
        end
      end
    end
  end

  def self.for(n)
    factors = []
    prime_stream = self.prime_stream
    next_prime = prime_stream.next
    sqrt_n = Math.sqrt(n)
    while n > 1 && next_prime <= sqrt_n
      if (n % next_prime).zero?
        n /= next_prime
        sqrt_n = Math.sqrt(n)
        factors << next_prime
      else
        next_prime = prime_stream.next
      end
    end
    factors << n if n > 1
    factors
  end
end

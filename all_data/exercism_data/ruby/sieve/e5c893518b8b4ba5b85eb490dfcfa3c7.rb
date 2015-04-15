class Sieve
  attr_reader :primes

  def initialize(limit)
    sieve = (0..limit).to_a
    sieve[1] = 0 # 1 isn't a prime

    # for every number from 2 to sqrt(limit)
    (2..Math.sqrt(limit).ceil).each do |divisor|
      # ... set to zero all multiples of this number in `sieve`
      (divisor*2..limit).step(divisor) { |n| sieve[n] = 0 }
    end

    # remaining non-zero numbers are prime
    @primes = sieve.reject(&:zero?)
  end
end

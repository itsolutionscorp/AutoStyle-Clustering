class Sieve
  def initialize(limit)
    @primes = []
    suspected_primes = (2..limit).to_a
    while suspected_primes.length > 0
      prime = suspected_primes.shift
      @primes.push(prime)
      suspected_primes.delete_if{|i| i % prime == 0}
    end 
  end

  attr_reader :primes
end

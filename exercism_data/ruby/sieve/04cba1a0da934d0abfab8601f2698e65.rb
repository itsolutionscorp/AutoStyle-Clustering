class Sieve
  attr_reader :calculate_primes_up_to
  def initialize(calculate_primes_up_to)
    @calculate_primes_up_to = calculate_primes_up_to
  end

  def primes
    potential_primes = (2..calculate_primes_up_to).to_a
    primes = []
    while(!potential_primes.empty?)
      prime = potential_primes.shift
      potential_primes = potential_primes.reject { |num| num%prime == 0 }
      primes << prime
    end
    primes
  end
end

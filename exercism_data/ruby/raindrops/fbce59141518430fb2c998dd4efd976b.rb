class Raindrops
  def self.convert(number)
    prime_factors = number.find_prime_factors
    plings = new(prime_factors).find_plings
    plings == "" ? number.to_s : plings
  end

  def initialize(primes)
    @primes = primes
    @pling_hash = { 1 => "", 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  end

  def find_plings
    @primes.uniq.reduce("") do |plings, prime|
      pling = @pling_hash[prime]
      pling.nil? ? plings : plings + pling
    end
  end

end

class Numeric

  def find_prime_factors(number = self)
    primes = []
    prime = (2..number).find do |i|
      number % i == 0
    end
    if prime.nil?
      primes.push(number)
    else
      primes.push(prime) + find_prime_factors(number / prime)
    end
  end
end

class Raindrops
  def self.convert(number)
    new(number).find_plings
  end

  def initialize(number)
    @number = number
    @pling_hash = { 1 => "", 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  end

  def find_plings
    primes = find_prime_factors
    calculate_plings(primes)
  end

  private

  def find_prime_factors(number = @number)
    primes = []
    prime = (2..number).detect do |i|
      number % i == 0
    end
    if prime.nil?
      primes.push(number)
    else
      primes.push(prime) + find_prime_factors(number / prime)
    end
  end

  def calculate_plings(primes)
    plings = primes.uniq.reduce("") do |plings, prime|
      pling = @pling_hash[prime]
      pling.nil? ? plings : plings + pling
    end

    plings == "" ? @number.to_s : plings
  end

end

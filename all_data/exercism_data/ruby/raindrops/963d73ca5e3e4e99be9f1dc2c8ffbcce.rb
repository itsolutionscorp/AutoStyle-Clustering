class Raindrops
  def self.convert(number)
    new(number).find_plings
  end

  def initialize(number)
    @number = number
    @pling_hash = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  end

  def find_plings
    if @number == 1
      "1"
    else
      primes = find_prime_factors
      calculate_plings(primes)
    end
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
    primes.uniq.reduce("") do |plings, prime|
      pling = @pling_hash[prime]
      pling.nil? ? plings : plings + pling
    end
  end

end

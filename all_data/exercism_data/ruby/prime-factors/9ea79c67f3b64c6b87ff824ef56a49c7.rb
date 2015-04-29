class PrimeFactors
  def self.for num
    factors = []
    prime = 1
    until num == 1
      prime = next_prime prime
      while num % prime == 0
        num = num/prime
        factors.push(prime)
      end
    end
    factors
  end

  private

  def self.next_prime current_prime
    next_prime = current_prime
    loop do
      next_prime += 1
      return next_prime if is_prime? next_prime
    end
  end

  def self.is_prime? prime
    (2..Math.sqrt(prime)).each do |num|
      return false if prime % num == 0
    end
    true
  end
end

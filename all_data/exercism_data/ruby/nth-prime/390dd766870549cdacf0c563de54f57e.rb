class Prime
  @@primes = [2,3]
  def self.nth(number)
    raise ArgumentError if number < 1
    possible_prime = @@primes.last+2
    while @@primes.length < number
      @@primes << possible_prime if Prime.is_prime?(possible_prime)
      possible_prime += 2
    end
    @@primes[number-1]
  end

  def self.is_prime?(possible_prime_number)
    (2..Math.sqrt(possible_prime_number)).each{|x|return false if possible_prime_number%x == 0 }
    return true
  end

  def self.array
    @@primes
  end
end

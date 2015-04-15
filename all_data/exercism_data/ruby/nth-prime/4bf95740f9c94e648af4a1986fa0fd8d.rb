class Prime
  def self.nth(number)
    raise ArgumentError if number < 1
    primes = [2]
    possible_prime = 3
    while primes.length < number
      primes << possible_prime if Prime.is_prime?(possible_prime)
      possible_prime += 2
    end
    primes.last
  end

  def self.is_prime?(possible_prime_number)
    (3..Math.sqrt(possible_prime_number)).step(2).each{|x|return false if possible_prime_number%x == 0 }
    return true
  end

end

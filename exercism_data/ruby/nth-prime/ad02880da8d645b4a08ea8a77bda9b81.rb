class Prime
  def self.nth(number)
    raise ArgumentError if number < 1
    primes = []
    possible_prime = 2
    while primes.length < number
      primes << possible_prime if Prime.is_prime?(possible_prime)
      possible_prime += 1
    end
    primes.last
  end

  def self.is_prime?(possible_prime_number)
    (2...possible_prime_number).each{|x|return false if possible_prime_number%x == 0 }
    return true
  end
end

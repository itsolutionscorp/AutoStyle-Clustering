module Raindrops
  # Keep a list of some known primes to help improve performance
  # of the prime checking method.
  KNOWN_PRIMES = [2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59]

  def self.convert(number)
    # Attempt to create the raindrop string. If the string is empty,
    # just return the number
    self.raindrop_string(self.prime_factorization(number)) || number.to_s
  end

  def self.raindrop_string(prime_factorization_array)
    raindrop_string = ""

    raindrop_string += "Pling" if prime_factorization_array.include? 3
    raindrop_string += "Plang" if prime_factorization_array.include? 5
    raindrop_string += "Plong" if prime_factorization_array.include? 7

    # Return nil if the string is empty
    raindrop_string.empty? ? nil : raindrop_string
  end

  def self.prime_factorization(number)
    # If the number is prime, it is its own
    # prime factorization
    return [] if number == 0
    return [] if number == 1

    KNOWN_PRIMES.each do | kp |
      remainder = number % kp
      remaining = number / kp
      if remainder == 0
        return [kp] + self.prime_factorization(remaining)
      end
    end
  end

end

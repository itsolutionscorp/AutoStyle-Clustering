module Rain
  class Math
    def self.important_primes
      [3, 5, 7]
    end

    # Returns an array of important primes for Rain,
    #   These will be in ascending order
    def self.important_prime_factorization(number)
      primes = []
      self.important_primes.each do |important_prime|
        primes << important_prime if number % important_prime == 0
      end
      primes
    end
  end

  class NoConversionForNumberError < StandardError; end

  class Words
    def self.from_number(number)
      case number
        when 3
          "Pling"
        when 5
          "Plang"
        when 7
          "Plong"
        else
          raise NoConversionForNumberError
      end
    end
  end
end

class Raindrops
  def self.convert(number)
   rain_string = ''
   important_primes = Rain::Math.important_prime_factorization(number)
   important_primes.each do |prime|
     rain_string << Rain::Words.from_number(prime)
   end 
   rain_string == '' ? number.to_s : rain_string
  end
end

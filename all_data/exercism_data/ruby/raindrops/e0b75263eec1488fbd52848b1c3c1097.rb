require 'prime'

module Raindrops

  module ClassMethods

    def convert(number)
      factors = prime_factors(number)

      prime_message(factors) || number.to_s
    end

    def prime_message(primes)
      primes.map! do |prime|
        case prime
        when 3
          "Pling"
        when 5
          "Plang"
        when 7
          "Plong"
        end
      end

      primes.compact.join unless primes.compact.empty?
    end

    def prime_factors(number)
      Prime.prime_division(number).map do |(prime, *count)|
        prime
      end
    end

  end

  extend ClassMethods

end

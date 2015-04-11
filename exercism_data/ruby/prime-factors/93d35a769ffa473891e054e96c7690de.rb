require "prime"

class PrimeFactors
  class << self
      
    def for number
      prime_division( number ).each_with_object[] do |pair, array|
        pair.last.times do 
          array << pair.first 
        end
      end
    end

  private

    def prime_division number
      Prime.prime_division number
    end

  end
end

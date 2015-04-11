# Warning! Last test takes forever.

require 'prime'

class PrimeFactors

  def self.for(number)
    factors = []
    Prime.each(number) do |prime|
      while number % prime == 0
        number = number / prime
        factors << prime
      end
    end
    factors
  end

end

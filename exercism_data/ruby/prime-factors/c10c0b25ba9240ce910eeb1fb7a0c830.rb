require 'prime'

class PrimeFactors
  def self.for(num)
    factors = []

    Prime.each(num) do |prime|
      while num % prime == 0
        factors << prime
        num /= prime
      end
      break if prime > num
    end

    factors
  end
end

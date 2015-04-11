require 'prime'

class PrimeFactors

  def self.for(num)
    prime_factors = []
    while num != 1
      Prime.each(num) do |prime|
        if num%prime==0
          prime_factors << prime
          num = num/prime
          break
        end
      end
    end
  return prime_factors
  end
end

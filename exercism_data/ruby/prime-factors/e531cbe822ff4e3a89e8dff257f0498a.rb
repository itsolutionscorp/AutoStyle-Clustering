require 'prime'

class PrimeFactors
  def self.for(n)
    Prime.prime_division(n).each_with_object([]) do |num, result|
      num[1].times do |no_use| 
        result.push(num[0])
      end
    end
  end
end

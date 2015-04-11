require 'prime'

class PrimeFactors
  def self.for num
    prime = (2..num).find { |prime| num % prime == 0 }
    !prime.nil? ? [prime].concat(self.for(num/prime)) : []
  end
end

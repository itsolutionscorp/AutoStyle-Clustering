require 'prime'

class PrimeFactors
  def self.for number
    division = Prime.prime_division number
    
    division.each_with_object([]) do |prime, response|
      (1..prime.last).each{ response << prime.first }
    end

  end
end

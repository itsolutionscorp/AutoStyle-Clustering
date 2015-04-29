require 'prime'

class PrimeFactors
  def self.for(num)
    Prime.prime_division(num).map{|arr| [arr[0]] * arr[1] }.flatten
  end
end

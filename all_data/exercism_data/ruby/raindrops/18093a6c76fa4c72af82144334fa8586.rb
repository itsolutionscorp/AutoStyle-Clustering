require 'prime'

class Raindrops

  def self.convert (number)
    return 1.to_s if number == 1 #prime_division returns nothing for 1!
    division = Prime.prime_division(number) #Prime.prime_division(28) returns [[2,2][7,1]] for 2,2,7
    prime_factors = []
    result = ""

    prime_factors = division.map { |a| a[0] } #capturing the factor, disregarding the counter.

    result << "Pling" if prime_factors.include?(3)
    result << "Plang" if prime_factors.include?(5)
    result << "Plong" if prime_factors.include?(7)
    result << number.to_s if result  == ""

    result
  end 
end

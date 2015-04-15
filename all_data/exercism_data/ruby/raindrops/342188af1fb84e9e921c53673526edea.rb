require 'prime'

class Raindrops
 def self.convert(number)
   result = ""

   result += 'Pling' if primes_of(number).include?(3)

   result += 'Plang' if primes_of(number).include?(5)

   result += 'Plong' if primes_of(number).include?(7)

   result = number.to_s if result == ""

   result

 end 

 def self.primes_of(number)
   number.prime_division.collect { |p| p[0] }   
 end

end

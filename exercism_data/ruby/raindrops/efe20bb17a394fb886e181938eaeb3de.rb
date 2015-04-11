require 'prime'

class Raindrops
  def self.convert(num)
    primes = num.prime_division.flat_map {|a, b| [a] * b }
   
    output = ""
    output << "Pling" if primes.include? 3
    output << "Plang" if primes.include? 5
    output << "Plong" if primes.include? 7

    output.empty? ? num.to_s : output
      
  end
end

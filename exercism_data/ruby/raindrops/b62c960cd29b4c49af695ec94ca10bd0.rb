require 'prime'
class Raindrops

  def self.convert number
    prime = number.prime_division.flatten
    if prime.any? { |x| [3, 5, 7].include?(x) }
      translate_to_rainspeak(prime)
    else
      number.to_s
    end

  end

  def self.translate_to_rainspeak primes
    arr = []
    primes.each {|prime| 
      if prime == 3 
        arr << "Pling" 
      elsif prime == 5
        arr << "Plang"
      elsif prime == 7
        arr << "Plong"
      end
    }
    arr.join("")
  end

end

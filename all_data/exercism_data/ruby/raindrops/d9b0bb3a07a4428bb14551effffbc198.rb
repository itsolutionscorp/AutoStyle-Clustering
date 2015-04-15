require 'prime'

class Raindrops
  def self.convert input
    primes = input.prime_division.flatten
    
    if primes.empty?
      return input.to_s
    end
    output = ""

    if has_three primes
      output += "Pling"
    end

    if has_five primes
      output += "Plang"
    end

    if has_seven primes
      output += "Plong"
    end

    if !has_three primes and !has_five primes and !has_seven primes
      output = input.to_s
    end

    output
  end

  private
  
    def self.has_three primes
      primes.count(3) > 0
    end

    def self.has_five primes
      primes.count(5) > 0
    end

    def self.has_seven primes
      primes.count(7) > 0
    end
end

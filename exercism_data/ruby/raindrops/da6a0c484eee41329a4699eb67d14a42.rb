#!/usr/bin/ruby

class Raindrops
  def self.convert(number)
    primes = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
    result = ""
    primes.each do |prime, string|
      result = result + converthelper(number, prime, string)
    end
    if result == ""
      return result= "#{number}"
    else
      return result
    end
  end
  def self.converthelper(number, prime, string)
    if (number % prime) == 0
      return string
    end
    return ""
  end
end

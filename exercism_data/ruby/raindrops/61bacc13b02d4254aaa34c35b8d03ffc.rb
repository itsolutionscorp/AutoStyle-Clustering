#!/usr/bin/ruby

class Raindrops
  def self.convert(number)
    primes = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
    result = ""
    primes.map {|prime, string| result += ((number%prime == 0) ? string : "")}
    result == "" ? "#{number}" : result
  end
end

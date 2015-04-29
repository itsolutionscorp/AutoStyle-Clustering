require 'prime'

class Raindrops

  def self.sounds
    { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
  end

  def self.convert(number)
    result = ""

    sounds.each_pair do |key, value|
      result += value if primes_of(number).include?(key)
    end

    result = number.to_s if result == ""

    result

  end 

  def self.primes_of(number)
    number.prime_division.collect { |p| p[0] }   
  end

end

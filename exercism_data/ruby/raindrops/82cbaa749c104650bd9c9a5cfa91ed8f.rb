require 'prime'

class Raindrops
  attr_accessor :number, :primes

  LANGUAGE = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def initialize(number)
    @number = number
    @primes = find_unique_primes(number)
  end

  def self.convert(number)
    raindrop = Raindrops.new(number)
    raindrop.raindroperize
  end

  def find_unique_primes(number)
    number.prime_division.flatten.select { |factor| factor.prime? }.uniq
  end

  def raindroperize
    rain_primes?(@primes) ? @number.to_s : @primes.map { |prime| LANGUAGE.fetch(prime, "")  }.reduce(:+)
  end

  def rain_primes?(primes)             #returns true if array only contains rain primes
    rain_primes = primes & [3,5,7]
    rain_primes.empty?
  end
end

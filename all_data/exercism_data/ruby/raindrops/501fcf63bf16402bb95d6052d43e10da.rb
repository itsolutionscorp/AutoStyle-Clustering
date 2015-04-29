require 'prime'

class Raindrops
  attr_reader :number
  
  PRIMES_TO_SOUNDS = { 3 => 'Pling',
                       5 => 'Plang',
                       7 => 'Plong' }

  def self.convert(number)
    new(number).sound_of_raindrops
  end

  def initialize(number)
    @number = number
  end

  def sound_of_raindrops
    rain_sounds = PRIMES_TO_SOUNDS.select do |(prime, _)|
      prime_factors.include?(prime)
    end.values
    
    rain_sounds.empty? ? number.to_s : rain_sounds.join
  end

  private

  def prime_factors
    @prime_factors ||= Prime.prime_division(number).map(&:first)
  end
end

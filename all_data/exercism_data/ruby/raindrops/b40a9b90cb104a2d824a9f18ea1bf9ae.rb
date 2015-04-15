require 'prime'

class Raindrops
  attr_reader :number

  def self.convert(number)
    new(number).rain_speak
  end

  def initialize(number)
    @number = number
  end

  def rain_speak
    rain_sounds.empty? ? number.to_s : rain_sounds.join
  end

  private

  def rain_sounds
    @rain_sounds ||= prime_to_sounds.select do |(prime, _)|
      prime_factors.include?(prime)
    end.values
  end

  def prime_factors
    @prime_factors ||= Prime.prime_division(number).map(&:first)
  end

  def prime_to_sounds
    {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }
  end
end

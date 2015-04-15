require 'prime'

class Raindrops
  PRIME_TO_SOUND = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert number
    sounds = ""
    factors = Prime.prime_division(number).map(&:first)

    PRIME_TO_SOUND.each_pair do |prime, sound|
      sounds << sound if factors.include? prime
    end
    sounds << number.to_s if sounds.empty?

    sounds
  end
end

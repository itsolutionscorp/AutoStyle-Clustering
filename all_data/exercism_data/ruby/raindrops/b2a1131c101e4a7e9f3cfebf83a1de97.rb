require "prime"

class Raindrops

  SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert number
    factors = Prime.prime_division(number).flatten.uniq.sort

    response = SOUNDS.each.map do |factor, sound|
      sound if factors.include? factor
    end.join

    response.empty? ? number.to_s : response
  end
end

class Raindrops
  SOUNDS_FOR_DIVISORS = [[3, "Pling"], [5, "Plang"], [7, "Plong"]]

  def self.convert(n)
    raindrop_sounds = SOUNDS_FOR_DIVISORS.map { |divisor, sound|
      sound if n.modulo(divisor).zero?
    }.compact
    raindrop_sounds.empty? ? n.to_s : raindrop_sounds.join("")
  end
end

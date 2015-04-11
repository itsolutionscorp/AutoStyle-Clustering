class Raindrops
  def self.convert(number)
    converted = raindrops(number)
    converted.empty? ? number.to_s : converted
  end

  def self.raindrops(number)
    rain_sounds = {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }

    rain_sounds.keys.map { |x| rain_sounds[x] unless number % x != 0 }.join
  end
end

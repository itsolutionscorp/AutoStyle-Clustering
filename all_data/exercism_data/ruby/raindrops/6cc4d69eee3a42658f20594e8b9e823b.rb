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

    [3, 5, 7].map { |x| rain_sounds[x] unless number % x != 0 }.join
  end
end

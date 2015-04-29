class Raindrops

  def self.convert(number_of_raindrops)
    rain_noise = ""
    rain_noise += "Pling" if number_of_raindrops % 3 == 0
    rain_noise += "Plang" if number_of_raindrops % 5 == 0
    rain_noise += "Plong" if number_of_raindrops % 7 == 0
    rain_noise.empty? ? number_of_raindrops.to_s : rain_noise
  end

end

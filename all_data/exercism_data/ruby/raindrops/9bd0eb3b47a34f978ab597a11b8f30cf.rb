class Raindrops

  @rain_sounds = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(number_of_drops)
    sound = ""
    sound << @rain_sounds[3] if number_of_drops % 3 == 0
    sound << @rain_sounds[5] if number_of_drops % 5 == 0
    sound << @rain_sounds[7] if number_of_drops % 7 == 0
    return number_of_drops.to_s if sound.empty?
    sound
  end

end

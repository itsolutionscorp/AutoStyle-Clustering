class Raindrops
  @drop_sounds = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert_sounds(drops)
    @drop_sounds.inject("") do |sound, sounds|
      sound << sounds[1] if drops % sounds[0] == 0; sound
    end
  end

  def self.convert(drops)
    sound = self.convert_sounds(drops)
    sound == "" ? drops.to_s : sound
  end
end

class Raindrops
  def self.convert(raindrop_number)
    raindrop_sounds = {3=>"Pling",
                       5=>"Plang",
                       7=>"Plong"}
    raindrop_sound = ""
    raindrop_sounds.each do |number, sound|
      raindrop_sound += sound if raindrop_number%number == 0
    end
    raindrop_sound = raindrop_number.to_s if raindrop_sound == ""

    raindrop_sound
  end
end

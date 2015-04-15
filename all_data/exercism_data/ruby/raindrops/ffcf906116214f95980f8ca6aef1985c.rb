class Raindrops

  SOUNDS = { 3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(number)
    final_sound = ""
    SOUNDS.each do |key, sound|
      final_sound << sound if (number % key) == 0
    end
    if final_sound == ""
      final_sound << number.to_s
    end
    final_sound
  end

end

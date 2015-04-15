class Raindrops

  SOUNDS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(n)
    sounds_to_use = SOUNDS.select { |k,v| n % k == 0 }
    sound_str = sounds_to_use.values.join
    sound_str.empty? ? n.to_s : sound_str
  end
end

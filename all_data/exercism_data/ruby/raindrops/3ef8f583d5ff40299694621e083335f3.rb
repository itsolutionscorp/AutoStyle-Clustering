class Raindrops

  def self.convert(n)
    sounds_to_use = sounds.select { |k,v| n % k == 0 }
    sound_str = sounds_to_use.values.join
    sound_str.empty? ? n.to_s : sound_str
  end

  def self.sounds
    @sounds ||= { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  end
end

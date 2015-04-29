class Raindrops
  def self.sounds
    {
      "3" => "Pling",
      "5" => "Plang",
      "7" => "Plong"
    }
  end

  def self.convert(number)
    drops = sounds.keys.each_with_object([]) do |key, array|
      array << sounds[key] if number % key.to_i == 0 
    end.join

    drops.length > 0 ? drops : number.to_s
  end
end

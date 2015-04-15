class Raindrops
  def self.convert(num)
    raindrop_sound = ""
    raindrop_sound += "Pling" if num % 3 == 0
    raindrop_sound += "Plang" if num % 5 == 0
    raindrop_sound += "Plong" if num % 7 == 0
    if raindrop_sound == ""
      raindrop_sound = num.to_s
    end
      raindrop_sound
  end
end

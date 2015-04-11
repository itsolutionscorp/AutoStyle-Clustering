class Raindrops
  NUMBER_SOUND = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(number)
    sounds = NUMBER_SOUND.select do |denominator, sound| 
      number % denominator == 0 
    end.values
    sounds.empty? ? number.to_s : sounds.join
  end
end

module Raindrops

  def self.convert(int)
    sounds = {3 => "Pling", 5 => "Plang", 7 => "Plong"}.map do |factor, sound|
      sound if int%factor == 0
    end
    sounds.any? ? sounds.join : int.to_s
  end

end

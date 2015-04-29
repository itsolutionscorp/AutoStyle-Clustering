class Raindrops

  def self.convert(drop)
    sound = ""
    sound += "Pling" if drop % 3 == 0
    sound += "Plang" if drop % 5 == 0
    sound += "Plong" if drop % 7 == 0
    sound = drop.to_s if sound.empty?
    sound
  end
end

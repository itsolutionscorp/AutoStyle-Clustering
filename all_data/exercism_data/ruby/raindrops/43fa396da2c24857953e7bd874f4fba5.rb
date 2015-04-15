class Raindrops
  def self.convert(drop)
    sound = ""
    sound << "Pling" if (drop % 3).zero?
    sound << "Plang" if (drop % 5).zero?
    sound << "Plong" if (drop % 7).zero?
    sound << drop.to_s if sound.empty?
    return sound
  end
end

class Raindrops
  def self.convert(drops)
    sound = "#{pling(drops)}#{plang(drops)}#{plong(drops)}"
    sound.empty? ? drops.to_s : sound
  end

  def self.pling(drops)
    sound_made('Pling', drops, 3)
  end

  def self.plang(drops)
    sound_made('Plang', drops, 5)
  end

  def self.plong(drops)
    sound_made('Plong', drops, 7)
  end

  def self.sound_made(sound, drops, number)
    sound if (drops % number).zero?
  end
end

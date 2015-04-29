class Raindrops
  SOUNDS = [[3, 'Pling'], [5, 'Plang'], [7, 'Plong']]

  def self.convert(n)
    sound = SOUNDS.map { |(factor, s)| n % factor == 0 ? s : '' }.join
    sound.empty? ? n.to_s : sound
  end
end

class Raindrops

  DROPS = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(num)
    drop_sounds = DROPS.map {|(value, sound)| sound if num % value == 0 }.join
    drop_sounds.empty? ? num.to_s : drop_sounds
  end
end

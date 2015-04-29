class Raindrops
  RAINDROP_SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def self.convert(n)
    melody = RAINDROP_SOUNDS.keys.select { |k| n % k == 0 }

    if melody.empty?
      n.to_s
    else
      melody.map { |drop| RAINDROP_SOUNDS[drop] }.join
    end
  end
end

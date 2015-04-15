class Raindrops
  def self.convert n
    sounds = []
    [[3, 'Pling'], [5, 'Plang'], [7, 'Plong']].each do |(factor, sound)| 
      sounds.push(sound) if n % factor == 0
    end
    if sounds.empty?
      n.to_s
    else
      sounds.join
    end
  end
end

class Raindrops

  RAINDROPS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(integer)

    raindrop_sounds = RAINDROPS.map { |x,y| integer % RAINDROPS.invert[y] == 0 ? RAINDROPS[x] : ''}.join
    raindrop_sounds.empty? ? integer.to_s : raindrop_sounds

  end
end

class Raindrops
  def self.convert(n)
    raindrop_sounds = []
    raindrop_sounds << "Pling" if n.modulo(3).zero?
    raindrop_sounds << "Plang" if n.modulo(5).zero?
    raindrop_sounds << "Plong" if n.modulo(7).zero?
    raindrop_sounds.empty? ? n.to_s : raindrop_sounds.join("")
  end
end

class Raindrops

  def self.convert n
    raindrop_speak = ""
    raindrop_speak += "Pling" if (n % 3).zero?
    raindrop_speak += "Plang" if (n % 5).zero?
    raindrop_speak += "Plong" if (n % 7).zero?
    raindrop_speak = n.to_s if raindrop_speak.empty?
    raindrop_speak
  end

end

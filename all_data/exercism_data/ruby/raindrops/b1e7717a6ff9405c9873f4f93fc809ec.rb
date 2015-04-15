class Raindrops
  def self.convert value
    raindrop_speak = ""
    raindrop_speak << "Pling" if value % 3 == 0
    raindrop_speak << "Plang" if value % 5 == 0
    raindrop_speak << "Plong" if value % 7 == 0
    raindrop_speak << "#{value}" if raindrop_speak.empty?
    raindrop_speak
  end
end

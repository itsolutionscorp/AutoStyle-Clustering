class Raindrops

  def self.convert(value)
    sound = ""
    sound << "Pling" if value % 3 == 0
    sound << "Plang" if value % 5 == 0
    sound << "Plong" if value % 7 == 0
    sound.length == 0 ? value.to_s : sound
  end

end

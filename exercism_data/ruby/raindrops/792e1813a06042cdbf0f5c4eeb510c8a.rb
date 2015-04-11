class Raindrops
  def self.convert(drops)
    sound = ""
    if drops % 3 == 0
      sound = "Pling"
    end

    if drops % 5 == 0
      sound = sound + "Plang"
    end

    if drops % 7 == 0
      sound = sound + "Plong"
    end

    if sound == ""
      sound = drops.to_s
    end
    sound
  end
end

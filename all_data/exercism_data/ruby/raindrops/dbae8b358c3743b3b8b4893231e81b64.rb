class Raindrops
  def self.convert(number)
    sounds = ""
    if self.primeFactor(3, number)
      sounds = sounds + "Pling"
    end

    if self.primeFactor(5, number)
      sounds = sounds + "Plang"
    end

    if self.primeFactor(7, number)
      sounds = sounds + "Plong"
    end

    if (sounds == "")
      sounds = number.to_s
    end

    sounds
  end

  private
  def self.primeFactor(number, seed)
    ((seed % number) == 0)
  end
end

class Raindrops
  def self.convert(num)
    rainspeak = ""
    if (num % 3 == 0)
      rainspeak += "Pling"
    end
    if (num % 5 == 0)
      rainspeak += "Plang"
    end
    if (num % 7 == 0)
      rainspeak += "Plong"
    end
    if (rainspeak == "")
      rainspeak = num.to_s
    end
    rainspeak
  end
end

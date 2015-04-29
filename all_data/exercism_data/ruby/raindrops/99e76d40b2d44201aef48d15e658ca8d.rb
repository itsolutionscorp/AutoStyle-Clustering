class Raindrops
  def self.convert(number)
    raindrop_speak = ""

    if(number % 3 == 0)
      raindrop_speak += "Pling"
    end

    if(number % 5 == 0)
      raindrop_speak += "Plang"
    end

    if(number % 7 == 0)
      raindrop_speak += "Plong"
    end

    if(raindrop_speak == "")
      number.to_s
    else
      raindrop_speak
    end
  end
end

class Raindrops

  def convert(number)
    raindrop_sound = plingify(number) + plangify(number) + plongify(number)
    if raindrop_sound.length > 0
      raindrop_sound
    else
      number.to_s
    end
  end

  def plingify(number)
    if number % 3 == 0
      "Pling"
    else
      ""
    end
  end

  def plangify(number)
    if number % 5 == 0
      "Plang"
    else
      ""
    end
  end

  def plongify(number)
    if number % 7 == 0 
      "Plong"
    else
      ""
    end
  end

end

class Raindrops

  def convert(number)
    pling = plingify(number)
    plang = plangify(number)
    plong = plongify(number)

    if pling || plang || plong
      "#{pling}#{plang}#{plong}"
    else
      number.to_s
    end
  end

  def plingify(number)
    if number % 3 == 0
      "Pling"
    end
  end

  def plangify(number)
    if number % 5 == 0
      "Plang"
    end
  end

  def plongify(number)
    if number % 7 == 0 
      "Plong"
    end
  end

end

class Raindrops

  def self.convert(num)
    pling = false
    plang = false
    plong = false
    ret = ""

    if (num % 3 == 0)
      pling = true
    end
    if (num % 5 == 0)
      plang = true
    end
    if (num % 7 == 0)
      plong = true
    end

    if pling
      ret << "Pling"
    end
    if plang
      ret << "Plang"
    end
    if plong
      ret << "Plong"
    end

    if !pling && !plang && !plong
      ret = "#{num}"
    end

    return ret
  end

end

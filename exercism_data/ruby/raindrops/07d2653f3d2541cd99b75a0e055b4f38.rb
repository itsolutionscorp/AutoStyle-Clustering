class Raindrops

  def self.convert( num )

    rstring = ""

    if num % 3 == 0
      rstring += "Pling"
    end

    if num % 5 == 0
      rstring += "Plang"
    end

    if num % 7 == 0
      rstring += "Plong"
    end

    if rstring == ""
      return num.to_s
    else
      return rstring
    end

  end

end

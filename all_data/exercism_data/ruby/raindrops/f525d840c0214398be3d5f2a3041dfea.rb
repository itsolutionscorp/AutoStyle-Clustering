class Raindrops
  def self.convert val
    out = ""
    if val % 3 == 0
      out += "Pling"
    end

    if val % 5 == 0
      out += "Plang"
    end

    if val % 7 == 0
      out += "Plong"
    end

    if out == ""
      val.to_s
    else
      out
    end
  end
end

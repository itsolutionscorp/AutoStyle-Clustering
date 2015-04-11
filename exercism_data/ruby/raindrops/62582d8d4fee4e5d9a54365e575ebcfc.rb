class Raindrops
  def self.convert(num)
    str = ""

    if num.remainder(3) == 0
      str += "Pling"
    end

    if num.remainder(5) == 0
      str += "Plang"
    end

    if num.remainder(7) == 0
      str += "Plong"
    end

    if str.empty?
      return num.to_s
    else
      return str
    end
  end
end

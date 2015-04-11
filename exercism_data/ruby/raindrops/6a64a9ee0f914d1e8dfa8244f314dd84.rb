class Raindrops
  def self.convert(num)
    ret = ""
    if (num % 3 == 0)
      ret += "Pling"
    end
    if (num % 5 == 0)
      ret += "Plang"
    end
    if (num % 7 == 0)
      ret += "Plong"
    end
    if ret == ""
      ret = num.to_s
    end
    ret
  end
end

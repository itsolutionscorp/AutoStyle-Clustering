class Raindrops
  def convert(num)
    s = ""
    if num % 3 == 0
      s += "Pling"
    end
    if num % 5 == 0
      s += "Plang"
    end
    if num % 7 == 0
      s += "Plong"
    end
    if s == ""
      s += num.to_s
    end
    s
  end
end

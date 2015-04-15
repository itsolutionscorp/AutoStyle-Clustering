class Raindrops
  def self.convert(num)
    s = ""
    if (num % 3 != 0) && (num % 5 != 0) && (num % 7 !=0)
      return num.to_s
    else
      if (num % 3 == 0)
        s << "Pling"
      end
      if (num % 5 == 0)
        s << "Plang"
      end
      if (num % 7 == 0)
        s << "Plong"
      end
      return s
    end
  end
end

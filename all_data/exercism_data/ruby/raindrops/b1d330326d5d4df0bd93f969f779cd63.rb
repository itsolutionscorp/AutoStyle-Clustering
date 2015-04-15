class Raindrops

  def self.convert(num)
    rain = ""
    if num % 3 == 0
      rain += "Pling" 
    end
    if num % 5 == 0
      rain += "Plang"
    end
    if num % 7 == 0
      rain += "Plong"
    end
    if rain == ""
      rain = num.to_s
    end
    return rain
  end
end

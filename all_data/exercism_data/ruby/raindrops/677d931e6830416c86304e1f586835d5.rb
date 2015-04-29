class Raindrops

  def self.convert(num)
    conversion = ""

    if num % 3 == 0
      conversion += "Pling"
    end
    if num % 5 == 0
      conversion += "Plang"
    end
    if num % 7 == 0
      conversion += "Plong"
    end
    if conversion == ""
      conversion = num.to_s
    end

    conversion
  end

end

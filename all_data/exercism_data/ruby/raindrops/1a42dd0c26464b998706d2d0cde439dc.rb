class Raindrops
  def self.convert(n)
  	return_string = ""
    if n % 3 == 0
      return_string += "Pling"
    end
    if n % 5 == 0
      return_string += "Plang"
    end
    if n % 7 == 0
      return_string += "Plong"
    end
    if n % 3 != 0 && n % 5 != 0 && n % 7 != 0
      return_string = n.to_s
    end
    return return_string
  end
end

class Raindrops
  def self.convert(n)
  	return n.to_s if n % 3 != 0 && n % 5 != 0 && n % 7 != 0
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
    return_string
  end
end
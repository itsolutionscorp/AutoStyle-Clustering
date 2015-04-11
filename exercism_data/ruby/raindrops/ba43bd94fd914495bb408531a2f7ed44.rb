class Raindrops
  def self.convert(num)
    str = ""
    if num % 3 == 0
      str += "Pling"
    end
    if num % 5 == 0
      str += "Plang"
    end
    if num % 7 == 0
      str += "Plong"
    end
    unless str == ""
      return str
    end
    return "#{num}"
  end
end

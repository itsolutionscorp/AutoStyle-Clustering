class Raindrops
  def self.convert(number)
    string = ""
    if number % 3 == 0
      string += "Pling"
    end
    if number % 5 == 0
      string += "Plang"
    end
    if number % 7 == 0
      string += "Plong"
    end
    if number % 3 != 0 && number % 5 != 0 && number % 7 != 0
      string += number.to_s
    end
    string
  end
end

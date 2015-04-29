class Raindrops


  def self.convert(number)

    counter = ""

    if number == 1
      "1"
    end
    if number % 3 == 0
      counter += "Pling"
    end
    if number % 5 == 0
      counter += "Plang"
    end
    if number % 7 == 0
      counter += "Plong"
    end
    if counter == ""
      counter = number
    end

    counter.to_s

  end

end

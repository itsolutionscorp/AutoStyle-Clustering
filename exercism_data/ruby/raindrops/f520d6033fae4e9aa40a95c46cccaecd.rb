class Raindrops
  def self.convert(number)

    accumulator = ""

    if (number % 3 == 0)
      accumulator += "Pling"
    end

    if (number % 5 == 0)
      accumulator += "Plang"
    end

    if (number % 7 == 0)
      accumulator += "Plong"
    end

    if (number % 3 != 0) && (number % 5 != 0) && (number % 7 != 0)
      accumulator += number.to_s
    end
    accumulator
  end
end

class Raindrops

  def self.convert(number)
    result = ''
    if (number % 3 == 0 || number % 5 == 0 || number % 7 == 0)
      if number % 3 == 0
        result << "Pling"
      end
      if number % 5 == 0
        result << "Plang"
      end
      if number % 7 == 0
        result << "Plong"
      end
    else
      result << number.to_s
    end
    result
  end
end

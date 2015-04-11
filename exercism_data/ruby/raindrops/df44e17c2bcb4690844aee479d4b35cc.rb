class Raindrops
  def convert number
    converted = ""
    if number % 3 == 0
      converted << "Pling"
    end
    if number % 5 == 0
      converted << "Plang"
    end
    if number % 7 == 0
      converted << "Plong"
    end
    if converted.empty?
      number.to_s
    else
      converted
    end
  end
end

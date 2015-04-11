class Raindrops
  def self.convert(number)
  to_return = "" 
    if number % 3 == 0
      to_return << "Pling"
    end

    if number % 5 == 0
      to_return << "Plang"
    end

    if number % 7 == 0
      to_return << "Plong"
    end

    if to_return.empty?
      number.to_s
    else
      to_return
    end
  end
end

class Raindrops

  # SingleResponibility translate number to raindrop

  def self.convert(number)
    output = []

    output << "Pling" if number % 3 == 0
    output << "Plang" if number % 5 == 0
    output << "Plong" if number % 7 == 0

    return output.any? ? output.join("") : number.to_s
  end

end

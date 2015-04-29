class Raindrops
  def self.convert(number)
    if (number % 3 == 0)
      "Pling"
    else
      number.to_s
    end
  end
end

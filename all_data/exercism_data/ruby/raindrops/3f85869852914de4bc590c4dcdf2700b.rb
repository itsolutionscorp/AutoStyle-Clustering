class Raindrops
  def self.convert(number)
    output = ""

    output << "Pling" if is_factor?(number, 3)
    output << "Plang" if is_factor?(number, 5)
    output << "Plong" if is_factor?(number, 7)
    output << number.to_s if output == ""

    output
  end

  def self.is_factor?(number, factor)
    number % factor == 0
  end
end

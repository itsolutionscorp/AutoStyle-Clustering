class Raindrops
  VALUES = {"Pling" => 3, "Plang" => 5, "Plong" => 7}
  def self.convert(number)

    word = ""
    while number > 0
      if number % 3 == 0
        word << "Pling" if !word.include?("Pling")
        number /= 3
      elsif number % 5 == 0
        word << "Plang" if !word.include?("Plang")
        number /= 5
      elsif number % 7 == 0
        word << "Plong" if !word.include?("Plong")
        number /= 7
      else
        break
      end
    end
    return number.to_s if word.empty?
    return word if !word.empty?
  end
end

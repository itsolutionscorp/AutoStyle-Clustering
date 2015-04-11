class Raindrops
  def self.contains_3(number)
    "Pling" if number % 3 == 0
  end

  def self.contains_5(number)
    "Plang" if number % 5 == 0
  end

  def self.contains_7(number)
    "Plong" if number % 7 == 0
  end

  def self.convert(number)
    output = ""
    output += contains_3(number) if contains_3(number) != nil
    output += contains_5(number) if contains_5(number) != nil
    output += contains_7(number) if contains_7(number) != nil
    output.empty? ? output = number.to_s : output
  end
end

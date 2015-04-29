class Raindrops
  def self.convert(input)
    string = ""
    string += "Pling" if factor_of_three?(input)
    string += "Plang" if factor_of_five?(input)
    string += "Plong" if factor_of_seven?(input)
    string += input.to_s if string.empty?
    string
  end

  def self.factor_of_three?(input)
    input % 3 == 0
  end

  def self.factor_of_five?(input)
    input % 5 == 0
  end

  def self.factor_of_seven?(input)
    input % 7 == 0
  end
end

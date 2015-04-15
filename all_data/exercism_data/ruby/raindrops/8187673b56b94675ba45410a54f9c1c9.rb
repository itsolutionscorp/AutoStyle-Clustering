class Raindrops

  def self.convert(input)
    result = ""
    result += "Pling" if input % 3 == 0
    result += "Plang" if input % 5 == 0
    result += "Plong" if input % 7 == 0
    result.empty? ? input.to_s : result
  end
end

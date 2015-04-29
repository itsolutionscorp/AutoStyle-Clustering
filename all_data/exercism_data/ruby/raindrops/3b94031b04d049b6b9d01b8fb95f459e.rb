module Raindrops
  def self.convert(number)
    res = ""
    res += "Pling" if number % 3 == 0
    res += "Plang" if number % 5 == 0
    res += "Plong" if number % 7 == 0
    res == "" ? number.to_s : res
  end
end

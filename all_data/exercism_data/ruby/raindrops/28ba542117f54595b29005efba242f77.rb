module Raindrops
  def self.convert(integer)
    result = ""
    result += "Pling" if integer % 3 == 0
    result += "Plang" if integer % 5 == 0
    result += "Plong" if integer % 7 == 0

    result.empty? ? integer.to_s : result
  end
end

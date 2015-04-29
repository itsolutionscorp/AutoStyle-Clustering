module Raindrops

  def self.convert num
    result = ""
    if num.modulo(3) == 0 then result += "Pling" end
    if num.modulo(5) == 0 then result += "Plang" end
    if num.modulo(7) == 0 then result += "Plong" end
    if result.empty? then num.to_s else result end
  end

end

module Raindrops
  def self.convert(i)
    output = ""

    if i % 3 == 0
      output += "Pling"
    end
    if i % 5 == 0
      output += "Plang"
    end
    if i % 7 ==0
      output +="Plong"
    end

    if output=="" #use integer if verbiage is empty
      output = i.to_s
    end

    output
  end
end

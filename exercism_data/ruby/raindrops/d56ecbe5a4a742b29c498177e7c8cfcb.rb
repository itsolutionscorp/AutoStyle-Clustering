module Raindrops
  extend self

  def convert(number)
    raindrops = ""
    if number % 3 == 0
      raindrops += "Pling"
    end
    if number % 5 == 0
      raindrops += "Plang"
    end
    if number % 7 == 0
      raindrops += "Plong"
    end
    if raindrops == ""
      "#{number}"
    else
      raindrops
    end
  end
end

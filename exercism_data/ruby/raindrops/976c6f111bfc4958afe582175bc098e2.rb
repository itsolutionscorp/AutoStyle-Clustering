require 'pry'

class Raindrops
  def self.convert(i)
    result = ""
    
    i % 3 == 0 ? result << "Pling" : ""
    i % 5 == 0 ? result << "Plang" : ""
    i % 7 == 0 ? result << "Plong" : ""

    result.empty? ? i.to_s : result
  end
end

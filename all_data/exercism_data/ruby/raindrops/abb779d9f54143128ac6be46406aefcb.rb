module Raindrops
  
  def self.convert no
    response = ""
    response << "Pling" if (no % 3 == 0)
    response << "Plang" if (no % 5 == 0)
    response << "Plong" if (no % 7 == 0)
    response = String(no) if response.empty?
    response
  end
  
end

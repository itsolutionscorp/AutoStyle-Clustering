module Raindrops

  def self.convert n
    result = ""
    result << "Pling" if n % 3 == 0
    result << "Plang" if n % 5 == 0
    result << "Plong" if n % 7 == 0
    result.empty? ? n.to_s : result
  end
end

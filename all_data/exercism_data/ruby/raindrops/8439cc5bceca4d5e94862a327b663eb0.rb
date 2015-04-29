module Raindrops
  STUFF = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert n
    result = ""
    STUFF.each do |thing|
      result += thing[1] if n % thing[0] == 0
    end
    result.empty? ? n.to_s : result
  end
end

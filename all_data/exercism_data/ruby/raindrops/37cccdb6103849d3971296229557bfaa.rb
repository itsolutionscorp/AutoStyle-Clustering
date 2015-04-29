module Raindrops
  def self.convert(x)
  	rain = String.new
  	rain << "Pling" if x % 3 == 0
  	rain << "Plang" if x % 5 == 0
  	rain << "Plong" if x % 7 == 0
  	return x.to_s if rain.empty?
  	rain
  end
end

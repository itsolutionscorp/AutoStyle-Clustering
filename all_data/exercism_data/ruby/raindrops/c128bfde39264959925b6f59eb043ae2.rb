module Raindrops
  def self.convert(number)
    rain = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
    answer = ""
    [3,5,7].each do |i|
	answer += number % i == 0 ? rain[i] : ""
    end
    answer == "" ? number.to_s : answer
  end
end

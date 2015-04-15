module Raindrops
  POSSIBLE = {
    "Pling" => 3,
    "Plang" => 5,
    "Plong" => 7
  }

  def self.convert(number)
    result(number).empty? ? number.to_s : result(number)
  end

  def self.result(number)
    POSSIBLE.map do |sound, value|
      sound if number % value == 0
    end.join
  end
end

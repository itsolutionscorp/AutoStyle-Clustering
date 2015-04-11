module Raindrops
  POSSIBLE = {
    "Pling" => 3,
    "Plang" => 5,
    "Plong" => 7
  }

  def self.convert(number)
    @number = number

    result.empty? ? number.to_s : result
  end

  def self.result
    POSSIBLE.map do |sound, value|
      sound if @number % value == 0
    end.join
  end
end

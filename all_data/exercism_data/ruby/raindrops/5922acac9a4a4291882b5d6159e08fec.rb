module Raindrops
  NOISES = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong" 
  }
  def self.convert(num)
    sound = NOISES.inject("") do |acc,(factor, noise)|
      acc += noise if (num % factor == 0)
      acc
    end
    return sound unless sound == ""
    num.to_s
  end
end

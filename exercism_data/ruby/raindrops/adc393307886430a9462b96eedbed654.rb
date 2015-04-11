module Raindrops

  def convert(num)
    rain = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }.reduce("") do |rain, (factor, drop)| 
      num % factor == 0 ? rain + drop : rain
    end
    rain.empty? ? num.to_s : rain
  end

  extend self
end

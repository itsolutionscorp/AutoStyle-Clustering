require 'prime'

class Raindrops

  @raindrop_speak = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(number)
    drops = ""

    number.prime_division.flatten.uniq.sort.each do |factor|
        drops << @raindrop_speak[factor].to_s if @raindrop_speak[factor]
    end

    drops.empty? ? number.to_s : drops
  end
end

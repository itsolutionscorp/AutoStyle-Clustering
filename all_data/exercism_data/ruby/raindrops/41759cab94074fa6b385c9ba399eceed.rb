class Fixnum
  def divisible_by?(number)
    (self % number).zero?
  end
end

class Raindrops
  @@types_of_drops = {
    Pling: 3,
    Plang: 5,
    Plong: 7
  }

  def self.convert(number)
    raindrops = ""
    @@types_of_drops.each do |sound, drops|
      raindrops << sound.to_s if number.divisible_by?(drops)
    end
    raindrops.empty? ? number.to_s : raindrops
  end
end

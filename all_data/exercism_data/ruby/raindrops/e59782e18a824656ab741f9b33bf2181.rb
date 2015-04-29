class Raindrops
  def self.rain_drops
    rain_drops = {3 => "Pling", 5 => "Plang", 7 => "Plong" }
  end

  def self.convert(drops)
    convert = String.new
    rain_drops.each { |is_prime, sound| convert += sound if drops % is_prime == 0  }
    convert.empty? ? "#{drops}" : convert
  end
end

class Raindrops
  PRIME_FACTORS = {
    3 => 'Pling', 5 => 'Plang', 7 => 'Plong'
  }

  def self.convert(number)
    raindrops = ""

    PRIME_FACTORS.each_pair do |factor, raindrop|
      raindrops << raindrop if number%factor == 0 
    end

    if raindrops == ""
      return "#{number}" 
    else
      return raindrops
    end
  end
end

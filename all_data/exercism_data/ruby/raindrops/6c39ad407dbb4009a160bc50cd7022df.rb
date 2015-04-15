class Raindrops
  RAINDROP = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }


  def self.convert(num)
    raindrops = ""
    RAINDROP.each do |prime_num, drop|
      raindrops += drop if num % prime_num == 0
    end
    if raindrops.empty?
      "#{num}"
    else
      raindrops
    end 
  end
end

class Raindrops
  def self.convert(num)
    raindrops = ""
    raindrops += 'Pling' if num % 3 == 0
    raindrops += 'Plang' if num % 5 == 0
    raindrops += 'Plong' if num % 7 == 0
    
    return num.to_s if raindrops == ""
    return raindrops
  end
end

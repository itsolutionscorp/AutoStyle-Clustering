class Raindrops
  def self.convert(number)
    compare(number)
  end

  private 
  def self.rules
    {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }
  end

  def self.compare(number)
    raindrops = rules.keys.select do |factor|
      number % factor == 0
    end
    if raindrops.any?
      new_raindrops = raindrops.map do |raindrop|
        rules[raindrop]
      end
      new_raindrops.join
    else
      number.to_s
    end    
  end
end

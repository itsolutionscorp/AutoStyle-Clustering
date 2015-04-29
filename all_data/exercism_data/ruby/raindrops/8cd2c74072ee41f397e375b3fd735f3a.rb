class Raindrops
  def self.convert(number)
    new.convert(number)
  end
  
  
  def convert(number)
    drops = drops(number)
    drops.empty? ? number.to_s : drops
  end
  
  
  def prime_drops
    {3 => "Pling", 5 => "Plang", 7 => "Plong"}
  end
  
  def drops(number)
    prime_drops.each_with_object("") {|(prime, drop), drops|
      drops << drop if number % prime == 0
    }
  end
end

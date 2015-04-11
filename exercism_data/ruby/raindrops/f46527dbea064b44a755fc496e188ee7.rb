class Raindrops
#Defining the class method here that reads a given number
  def self.convert(number)
    new.convert(number)
  end
#Defining the instance method
  def convert(number)
    drops = drops(number)
#Here the check and return if no drop is get. As the test request    
    drops.empty? ? number.to_s : drops
  end
#A method that returns a hash with the possible events stored
  def prime_drops
    {3 => "Pling", 5 => "Plang", 7 => "Plong"}
  end
#The method that checks the given number
  def drops(number)
#Iterating through de prime_drops hash thanks to the Public Instance Method .each_with_object
    prime_drops.each_with_object("") {|(prime, drop), drops|
#While we iterate we check the number looking for the prime factor and storing it if TRUE
      drops << drop if number % prime == 0
    }
  end
end

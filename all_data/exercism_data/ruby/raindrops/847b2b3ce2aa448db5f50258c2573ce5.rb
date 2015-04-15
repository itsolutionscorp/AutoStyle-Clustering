require "prime"
class Raindrops

  NOISES = {
    3=> "Pling",
    5=> "Plang",
    7=> "Plong",
  }

  def self.factorisation(number)
    prime_array = []
    Prime.each(number){|prime| prime_array << prime  }
    factors = []
    i = 1
    while i < prime_array.length
      if number % prime_array[i] ==0
        factors << prime_array[i]
        number = number / prime_array[i]
      end
      i += 1
    end
    factors
  end


  def self.convert(number)
    factors = self.factorisation(number)
    drops = []
    NOISES.each{|key, value| drops << value if factors.include?(key)}
    drops << number if !(factors & [3,5,7]).any?
    drops.join("")
  end

end

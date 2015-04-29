require "prime"

class Raindrops

  @rain_sounds = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(number_of_drops)
    list_of_primes = Prime.prime_division(number_of_drops).map{ |x| x[0] }
    sound = ""
    sound << @rain_sounds[3] if list_of_primes.include? 3
    sound << @rain_sounds[5] if list_of_primes.include? 5
    sound << @rain_sounds[7] if list_of_primes.include? 7
    return number_of_drops.to_s if sound.empty?
    sound
  end

end

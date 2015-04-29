require 'pry'

class Raindrops
  PRIMES = {3 => 'Pling', 5 => "Plang", 7 => "Plong"}

  def self.convert(number)
    result = ""
    PRIMES.each do |prime, rain_sound|
      if number % prime == 0
        result << rain_sound
      end
    end
    result = result != "" ? result : number.to_s
  end
end

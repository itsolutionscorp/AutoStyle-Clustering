require 'prime'

class Raindrops

  def self.convert(num)
    return num.to_s if num == 1
    drops = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
    sounds = []

    primes, powers = num.prime_division.transpose
    primes.each do |factor|
      unless sounds.include?(drops[factor])
        if factor == 3 || factor == 5 || factor == 7
          sounds << drops[factor]
        end
      end
    end

    sounds << num.to_s unless sounds.length > 0

    sounds.join('')
  end
end

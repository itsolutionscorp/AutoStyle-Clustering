require 'prime'

class Raindrops
  SOUNDS = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(number)
    i = 0
    x = number
    primes = Prime.first 100
    sounds = String.new
    last_sound = String.new

    while x > 1 do

      if x.to_f / primes[i] % 1 != 0
        i += 1
      else
        x = x / primes[i]
        if primes[i] == 3 || primes[i] == 5 || primes[i] == 7
          (sounds << SOUNDS[primes[i]] && last_sound = SOUNDS[primes[i]]) if last_sound != SOUNDS[primes[i]]
        end
      end
    end

    if sounds.empty?
      sounds << "#{number}"
    end

    sounds
  end
end

require 'prime'

class Raindrops
  def self.convert(number)
    self.new().convert(number)
  end

  def convert(number)
    raindrops = ''

    prime_sound_pairs = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong',
    }

    raindrops = prime_sound_pairs.inject('') do |raindrops, (prime, sound)|
      if number % prime === 0
        raindrops += sound
      end
      raindrops
    end

    raindrops.empty? ? raindrops : number.to_s
  end

end

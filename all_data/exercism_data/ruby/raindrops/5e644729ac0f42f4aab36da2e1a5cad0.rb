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

    raindrops = prime_sound_pairs.select do |prime|
      number % prime === 0
    end
    .values
    .join

    return raindrops unless raindrops.empty?
    number.to_s
  end

end

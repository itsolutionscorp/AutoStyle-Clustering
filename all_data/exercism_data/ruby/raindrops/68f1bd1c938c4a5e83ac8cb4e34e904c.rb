require 'prime'

class Raindrops
  def self.convert(number)
    self.new().convert(number)
  end

  def convert(number)
    raindrops = ''

    # prime_division returns pairs of factors and how often it is a divisor of the number
    # we only need the factors
    prime_factors = Prime.prime_division(number).map do |pair|
      pair[0]
    end

    # no hash, because order is significant
    prime_sound_pairs = [
      [3, 'Pling'],
      [5, 'Plang'],
      [7, 'Plong'],
    ]

    raindrops = prime_sound_pairs.inject('') do |raindrops, prime_sound_pair|
      if prime_factors.include?(prime_sound_pair[0])
        raindrops += prime_sound_pair[1]
      end
      raindrops
    end

    raindrops.length > 0 ? raindrops : number.to_s
  end

end

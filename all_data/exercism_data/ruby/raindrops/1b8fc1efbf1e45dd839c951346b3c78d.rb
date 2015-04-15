require 'prime'

class Raindrops
  RAINDROP_SPEAK = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
  }

  def self.convert(number)
    prime_factors = unique_prime_factors_array(number)

    # return original number if no raindrop-speak needed
    return number.to_s unless raindrop_speak_needed?(prime_factors)

    translate_to_raindrop_speak(prime_factors)
  end

  private

  def self.unique_prime_factors_array(number)
    Prime.prime_division(number).uniq.flatten
  end

  def self.raindrop_speak_needed?(prime_factors)
    prime_factors.any? { |f| RAINDROP_SPEAK.keys.include? f}
  end

  def self.translate_to_raindrop_speak(prime_factors)
    raindrop_speak = ''
    RAINDROP_SPEAK.each do |factor, translation|
      raindrop_speak += translation if prime_factors.include? factor
    end

    raindrop_speak
  end
end

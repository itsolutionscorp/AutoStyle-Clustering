require 'prime'

class Raindrops
  PRIMES_TO_SOUNDS = { 3 => 'Pling',
                       5 => 'Plang',
                       7 => 'Plong' }

  def self.convert(number)
    rain_sounds = prime_factors(number).map do |prime|
      PRIMES_TO_SOUNDS[prime]
    end

    rain_sounds.any? ? rain_sounds.join : number.to_s
  end

  def self.prime_factors(number)
    Prime.prime_division(number).map(&:first)
  end
  private_class_method :prime_factors
end

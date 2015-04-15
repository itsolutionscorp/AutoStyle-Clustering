require 'prime'

class Raindrops
  RAIN_TRANSLATOR = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    rain_sounds = sounds(primes(number))
    rain_sounds.empty? ? number.to_s : rain_sounds
  end

  def self.primes(number)
    number.prime_division.map(&:first)
  end

  def self.sounds(numbers)
    numbers.reduce("") { |str, n| str += RAIN_TRANSLATOR[n].to_s }
  end
end

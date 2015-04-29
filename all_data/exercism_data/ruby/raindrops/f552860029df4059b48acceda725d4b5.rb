require 'prime'

class Raindrops
  RAIN_TRANSLATOR = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    rain_words = trasnlate_to_rainspeak(prime_factors(number))
    rain_words.empty? ? number.to_s : rain_words
  end

  def self.prime_factors(number)
    number.prime_division.map(&:first)
  end

  def self.trasnlate_to_rainspeak(numbers)
    numbers.map { |e| RAIN_TRANSLATOR[e] }.compact.join
  end
end

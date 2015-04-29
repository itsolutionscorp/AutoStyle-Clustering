require 'prime'

class Raindrops
  RAIN_TRANSLATOR = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    factors = number.prime_division.map(&:first)
    rain_words = factors.map{ |e| RAIN_TRANSLATOR[e] }.compact.join
    rain_words.empty? ? number.to_s : rain_words
  end
end

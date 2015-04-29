require 'prime'

class Raindrops
  TRANSLATIONS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    translated = prime_factors(number)
                   .map { |factor| TRANSLATIONS[factor] }
                   .join
    translated = number.to_s if translated == ''
    translated
  end

private

  def self.prime_factors(number)
    number.prime_division.map(&:first)
  end
end

require 'prime'

module Raindrops
  TRANSLATION = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    words = prime_factors(number) & TRANSLATION.keys
    if words.any?
      words.map { |word| TRANSLATION[word] }.join
    else
      number.to_s
    end
  end

  def self.prime_factors(number)
    Prime.prime_division(number).map(&:first)
  end
end

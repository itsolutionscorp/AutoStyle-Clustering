require 'prime'

module Raindrops
  FACTOR_TRANSLATIONS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(n)
    words = n.prime_division.map { |factor, _| FACTOR_TRANSLATIONS[factor] }
    if words.any?
      words.join
    else
      n.to_s
    end
  end
end

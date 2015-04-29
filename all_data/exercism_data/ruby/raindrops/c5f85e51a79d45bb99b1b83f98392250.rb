require 'prime'

class Raindrops
  NUM_TO_STR_MAP = Hash.new('').merge({3 => 'Pling', 5 => 'Plang', 7 => 'Plong'})

	def self.convert(no_of_drops)
    factors = prime_factors(no_of_drops)
    (factors & NUM_TO_STR_MAP.keys).empty? ? no_of_drops.to_s : factors.inject("") { |str, n| str += NUM_TO_STR_MAP[n] }
	end

  def self.prime_factors(number)
    Prime.prime_division(number).flat_map { |factor, power| [factor] * power }.uniq
  end
end

require 'prime'
class Raindrops
	@mappings = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

	def self.convert(number)
		primes = number.prime_division.reduce(&:+) || []
		drops = (primes.uniq - [1]).map {|n| 
			@mappings[n]
		}.join 
		drops == '' ? number.to_s : drops
	end
end

require 'prime'
class Raindrops
	@mappings = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong', 1 => ''}

	def self.convert(number)
		# get rid of prime division
		primes = number.prime_division.reduce(&:+) || []

		drops = (primes.uniq).map {|n| 
			@mappings[n]
		}.join 
		drops == '' ? number.to_s : drops
	end
end

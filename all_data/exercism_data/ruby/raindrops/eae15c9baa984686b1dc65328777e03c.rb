require 'prime'

class Raindrops
	RAINMAP = {
		3 => 'Pling',
		5 => 'Plang',
		7 => 'Plong'
	}

	def self.convert(number)
		primes = number.prime_division.flatten
		drops = RAINMAP.keys.select{|factor| primes.include? factor }.map{|factor| RAINMAP[factor]}.join
		drops.empty? ? number.to_s : drops
	end

end

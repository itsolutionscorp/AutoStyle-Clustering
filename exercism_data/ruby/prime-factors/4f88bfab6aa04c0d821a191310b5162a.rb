require 'prime'

class PrimeFactors
	def self.for(x)
		pf = []
		x.prime_division.each do |p|
			p[1].times {pf << p[0]}
		end
		pf
	end
end

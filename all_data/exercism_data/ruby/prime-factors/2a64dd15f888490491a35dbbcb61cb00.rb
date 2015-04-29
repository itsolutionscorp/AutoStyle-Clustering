
class PrimeFactors

	def self.for(integer, factors = [])
		return factors if integer == 1		
    factor_range = (2..[integer/3,3].max)

		low_factor = factor_range.step.detect{|factor|
		integer % factor == 0
		} || integer

		PrimeFactors.for(integer/low_factor, factors << low_factor)
	end
	
end

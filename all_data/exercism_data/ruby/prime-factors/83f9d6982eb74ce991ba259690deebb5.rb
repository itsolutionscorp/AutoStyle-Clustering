module PrimeFactors
	def self.for(dividend)
		Array.new.tap do |result|
			divisor = 2
			while( dividend > 1 )
				quotient, remainder = dividend.divmod(divisor)
				if remainder == 0
					result << divisor
					dividend = quotient
				else
					divisor += 1
				end
			end
		end
	end
end

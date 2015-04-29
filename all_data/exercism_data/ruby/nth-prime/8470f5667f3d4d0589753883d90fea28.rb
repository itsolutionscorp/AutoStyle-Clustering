require "prime"
class Prime

	def self.nth( nPrime )
		if nPrime == 0
			raise ArgumentError
		else
			Prime.first(nPrime).last
		end
	end

end

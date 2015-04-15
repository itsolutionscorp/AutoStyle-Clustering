require 'prime'

class PrimeFactors

	def self.for(num)
		
		factors = []
		n = Prime.instance.each
		p = n.succ

		until num == 1
			until num%p != 0
				factors << p
				num/=p
			end
			p = n.succ
		end

		factors

	end

end

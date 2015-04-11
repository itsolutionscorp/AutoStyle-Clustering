require 'prime'

module Raindrops
		
	DROPS= ['Pling', 'Plang', 'Plong']
	PRIMES = Prime.first(4)[1..4]
	DROP_ON  = Hash[PRIMES.zip(DROPS)] 

	extend self

	def drip(number)
		drops = ''
		PRIMES.each do |prime| 
			drops << DROP_ON[prime] if number % prime == 0
		end
		drops		
	end

	def convert(number)
		return "#{number}" if (drip number) == ''
		return drip number
	end
end

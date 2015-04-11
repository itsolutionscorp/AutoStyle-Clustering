require 'prime'

class Hash
	def fill(keys, values)
		keys.length.times do |index|
			self[keys[index]] = values[index]
		end
		self
	end
end

module Raindrops
		
	primes = Prime.first(4)[1..4]  			# exclude first prime
	drops = ['Pling', 'Plang', 'Plong']
	DROPS = Hash.new.fill(primes,drops)

	extend self

	def convert(number)
		factors = Prime.prime_division(number).flatten.uniq & DROPS.keys
		rain = ''
		factors.each do |factor|
			rain += DROPS[factor]
		end
		return "#{number}" if rain == ''
		return rain 
	end
end

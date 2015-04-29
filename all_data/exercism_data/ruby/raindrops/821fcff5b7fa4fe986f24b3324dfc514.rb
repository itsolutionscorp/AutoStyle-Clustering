require 'prime'
require 'set'

module Raindrops

	extend self

	DROPS = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

	def convert(number)
		rain = ''
		factors(number).each do |factor|
			rain += DROPS[factor] if DROPS.keys.include? factor
		end
		return "#{number}" if rain == ''
		return rain
	end
	
	def factors(number)
		factors = Set.new
		Prime.each do |prime|
			while (number % prime) == 0 do
				factors << prime
				number = number / prime
			end
			break if prime > number
		end
		factors
	end

end

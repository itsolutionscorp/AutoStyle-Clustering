require 'prime'

class Raindrops
	def self.convert(n)
		factors = Prime.prime_division(n).flatten
		drop = ''
		drop += 'Pling' if factors.include? 3
		drop += 'Plang' if factors.include? 5
		drop += 'Plong' if factors.include? 7
		drop == '' ? n.to_s : drop
	end
end

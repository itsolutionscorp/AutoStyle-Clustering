# the cheap, un-Ruby version

require 'prime'

class Raindrops

	def self.convert n

		factorized = Prime.prime_division(n).flatten.delete_if { |n| n == 1 }

		if (factorized & [3,5,7]).empty?
			n.to_s
		else
			(factorized & [3,5,7]).sort.join.sub('3','Pling').sub('5','Plang').sub('7','Plong')
		end

	end

end

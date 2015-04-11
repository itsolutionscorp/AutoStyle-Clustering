require 'prime'

class Raindrops
	def self.convert(a)
		if a.prime? == false
			a.to_s
		end
	end
end

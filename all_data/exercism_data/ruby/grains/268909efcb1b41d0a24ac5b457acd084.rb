class Grains

	def initialize
	end

	def square(number)
		grains = 1
		(number-1).times{ grains *= 2}
		grains
	end

	def total
		total = 0
		grains = 1
		64.times do |tile_number|
			total += grains
			grains *= 2
		end	
		total	
	end

end

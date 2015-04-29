class Grains
	def initialize
		@grains_array = []
		counter = 1
		64.times do |x|
			@grains_array << counter
			counter *= 2
		end
	end
	
	def square(n)
		@grains_array[n.pred]
	end

	def total
		@grains_array.reduce(:+)
	end
end

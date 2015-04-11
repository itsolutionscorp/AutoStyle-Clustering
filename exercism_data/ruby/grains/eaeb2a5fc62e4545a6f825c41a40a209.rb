class Grains
	def initialize
		@squares = 64
	end

	def square(sq)
		return 2**(sq-1)
	end

	def total
		total = 0
		@squares.times do |sq|
			total +=square(sq + 1)
		end
		total
	end

end

class Grains
	attr_reader :total

	def initialize
		@total = 0

		(0..63).each{ |power|
			@total += 2 ** power
		}
	end

	def square power
		2 ** (power - 1)
	end

end

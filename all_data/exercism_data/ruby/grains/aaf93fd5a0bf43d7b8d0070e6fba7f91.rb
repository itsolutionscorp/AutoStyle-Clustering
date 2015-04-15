class Grains
	def square(number)
		return 2 ** (number - 1)
	end

	def total
		(1..64).inject {|total, number|
			total + square(number)
		}
	end
end

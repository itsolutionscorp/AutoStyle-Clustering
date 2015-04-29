class Grains
	def square(number)
		return 2 ** (number - 1)
	end

	def total
		total = 0
		(1..64).each do |number|
			total += square(number)
		end
		return total
	end
end

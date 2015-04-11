class Grains


	def square(grain)
		2 ** (grain-1)
	end

	def total
		sum = 0
		65.times do |number|
			sum += square(number)
		end
		return sum.to_i
	end

end

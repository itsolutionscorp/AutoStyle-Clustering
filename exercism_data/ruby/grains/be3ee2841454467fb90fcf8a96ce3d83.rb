class Grains
	def square(number)
		grains = 0
		(1..number).each do
			if grains == 0
				grains = 1
			else
				grains = grains*2
			end
		end

		grains
	end

	def total
		grains = 0
		total = 0
		(1..64).each do
			if grains == 0
				grains = 1
				total = 1
			else
				grains = grains*2
				total += grains
			end
		end

		total
	end
end

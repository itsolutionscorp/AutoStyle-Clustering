class Grains

	def square(square)
		grain = 1
		(square -1).times { grain = grain * 2 }
		grain
	end

	def total
		grain, total = 1, 1
		(63).times do
			grain = grain * 2
			total += grain
		end
		total 
	end
end

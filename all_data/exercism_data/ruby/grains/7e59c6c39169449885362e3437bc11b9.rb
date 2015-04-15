class Grains
	def square(sq)
		grains_on_a_square = 1
		if sq == 1
			grains_on_a_square
		else
			(2..sq).each do | a_square |
				grains_on_a_square = grains_on_a_square * 2
			end
		end
		return grains_on_a_square
	end
	def total
		total_grains = 1
		(2..64).each do |i|
			total_grains += square(i)
		end
		total_grains
	end
end

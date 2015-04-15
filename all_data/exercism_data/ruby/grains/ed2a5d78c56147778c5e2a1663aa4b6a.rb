class Grains
	def square(pos)
		if (pos == 1)
			1
		else
			2 * square(pos - 1)
		end
	end
	
	def total
		prev = result = 1
		
		for pos in 2..64
			prev = 2 * prev
			result += prev
		end
		
		return result
	end
end

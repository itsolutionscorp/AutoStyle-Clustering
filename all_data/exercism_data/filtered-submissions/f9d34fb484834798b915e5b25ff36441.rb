def compute (a,b)
		total_differences = 0
		if a.length >= b.length
			counter = b.length
		else
			counter = a.length
		end

		until counter == 0
			counter -= 1
			if a[counter] != b[counter]
				total_differences += 1
			end
		end
		return total_differences













	end
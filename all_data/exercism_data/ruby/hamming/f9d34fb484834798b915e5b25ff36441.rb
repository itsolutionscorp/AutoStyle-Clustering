class Hamming
	def self.compute (a,b)
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








		# if a==b
		# 	return 0
		# else 
		# 	return 1
		# end	
	end
end

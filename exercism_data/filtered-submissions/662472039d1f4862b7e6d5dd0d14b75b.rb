def compute(left, right)
	
		diff = 0
		#for i in 0..left.length
		i = 0
		while (i < left.length && i < right.length) 
			if(left[i] != right[i])
				diff+=1
			end
			i += 1
		end
		
		diff
	end
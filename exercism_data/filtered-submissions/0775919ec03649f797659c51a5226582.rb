def compute a, b
		count = 0
		a.length.times do |i|
			if a[i] != b[i]
				count+=1
			end
		end	
		return count
	end
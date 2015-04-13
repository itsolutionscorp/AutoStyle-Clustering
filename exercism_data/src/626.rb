def compute(a,b)
		if a.length > b.length
			a = a[0..b.length-1]
		else
			b = b[0..a.length-1]
		end

		i = 0
		count = 0
		while i < a.length
			if a[i] != b[i]
				count += 1
			end
			i += 1
		end
		return count
	end
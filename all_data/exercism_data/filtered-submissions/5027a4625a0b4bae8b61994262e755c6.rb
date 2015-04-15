def compute(a,b)
		i = 0
		count = 0
		while a.length-1>=i and b.length-1>=i do
			if a[i]!=b[i]
				count = count + 1
			end
			i+=1
		end
		return count
	end
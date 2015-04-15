def compute(a,b)
		a,b = [a,b].sort { a.length <=> b.length }
		a.chars.zip(b.chars).count{ |a| a[0] != a[1] }
	end
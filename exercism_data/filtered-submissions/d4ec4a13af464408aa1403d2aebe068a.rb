def compute a, b
		n=0
		[a.length, b.length].min.times{ |i| n += 1 unless a[i] == b[i] }
		n
	end
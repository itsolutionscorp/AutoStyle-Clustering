def compute(x, y)
		l = x.length
		if y.length < l then l = y.length end

		hamming = 0

		(0..l-1).each do |i|
			if x[i] != y[i] then hamming += 1 end
		end

		return hamming
	end
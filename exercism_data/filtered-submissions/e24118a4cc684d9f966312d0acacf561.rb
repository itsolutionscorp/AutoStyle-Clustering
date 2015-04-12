def compute(x, y)
		[x, y].min{ |x, y| x.size <=> y.size }.size.times.count { |pos| x[pos] != y[pos] }
	end
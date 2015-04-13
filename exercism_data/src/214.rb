def compute(x,y)
		hamming_counter = 0
		size_control = x.size unless y.size < x.size ? size_control = y.size : size_control
		(0...size_control).each do |var|
			hamming_counter += 1 unless x[var] == y[var]
		end
		return hamming_counter
	end
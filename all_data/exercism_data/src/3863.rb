def compute(a, b)
	distance = 0

	if a > b then
	    a.length
	else
	    b.length
	end.times { |i|
	    distance += 1 unless a[i] == b[i]
	}

	return distance
    end
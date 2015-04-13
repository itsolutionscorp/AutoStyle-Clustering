def compute(a,b)
		raise "lengths do not match" if a.length != b.length
		zipped = a.chars.zip(b.chars)
		distance = zipped.select{ |(a,b)| a != b }.count
		return distance
	end
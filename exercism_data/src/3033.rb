def compute(strand1,strand2)
		compare_length = [strand1.length,strand2.length].min
		hamming_distance = compare_length.times.count{ |n|
			strand1[n] != strand2[n]
		}
	end
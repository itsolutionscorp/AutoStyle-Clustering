def compute(strand1,strand2)
		compare_length = [strand1.length,strand2.length].min
		hamming_distance = (0..compare_length-1).map{ |n|
			strand1[n] == strand2[n] ? 0 : 1
		}.reduce(:+)
	end
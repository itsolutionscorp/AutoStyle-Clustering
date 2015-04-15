def compute(strand1,strand2)
		compare_length = [strand1,strand2].sort_by(&:length).first.length # get length of shorter strand
		distance = (0..compare_length-1).map{ |n|
			strand1[n] == strand2[n] ? 0 : 1
		}.reduce(:+)
	end
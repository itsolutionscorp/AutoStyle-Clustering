def compute(strand_1,strand_2)
		error_count = 0
		for n in 0..(strand_1.length)
			error_count += 1 if strand_1[n] != strand_2[n]
		end
		return error_count
	end
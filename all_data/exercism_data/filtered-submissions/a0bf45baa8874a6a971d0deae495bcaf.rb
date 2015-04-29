def compute(strand1,strand2)
		strand1.split("").each_with_index.select { |g, i| g != strand2[i] }.length
	end
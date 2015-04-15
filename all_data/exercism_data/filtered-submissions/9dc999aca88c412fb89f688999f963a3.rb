def compute(s1, s2)
		distance = 0

		for i in 0...[s1.length, s2.length].min
			if s1[i] != s2[i]
			distance += 1
			end
		end

		return distance
	end

	if ARGV[0] and ARGV[1]
		strand1 = ARGV[0].downcase
		strand2 = ARGV[1].downcase

		puts compute(strand1, strand2)
	end
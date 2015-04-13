def compute (strand_a, strand_b)

		if strand_a == '' or strand_b == ''
			return 0


		elsif strand_a[0] != strand_b[0]
			return 1 + compute(strand_a[1..-1], strand_b[1..-1])


		else
			return compute(strand_a[1..-1], strand_b[1..-1])
		end
	end
def compute(dna1, dna2)
		return 0 if dna1 == dna2 # save some computation time if equal
		d1 = dna1.chars
		dna2.chars.map { |c| (c == d1.next) ? 0 : 1 }.reduce(&:+)
	end
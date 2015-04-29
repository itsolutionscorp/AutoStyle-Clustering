def compute(dna_1_string, dna_2_string)
		dna_2_string.chars.zip(dna_1_string.chars)
            .take_while { |_, s| not(s.nil?) }
            .count { |p1, p2| p1 != p2 }
	end
def compute( strand_a, strand_b )

		a_chars = strand_a.chars
		b_chars = strand_b.chars

		a_chars.zip( b_chars ).count { | x, y | x != y }

	end
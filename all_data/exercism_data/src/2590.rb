def compute( a, b )

		a_chars = a.chars.to_a
		b_chars = b.chars.to_a

		distance = 0

		a_chars.each_with_index { | a_char, i |

			distance += 1 if a_char != b_chars[i]

		}

		distance

	end
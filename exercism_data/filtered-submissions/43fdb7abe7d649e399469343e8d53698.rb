def compute(a,b)

			return a <=> b if (a <=> b) == 0

			h = 0
			a.chars.each.with_index do | char, i |
				h += 1 if char != b.split('')[i]
			end

			h
		end
def compute(a,b)
			# if the strings are the same no need for further evaluation
			return a <=> b if (a <=> b) == 0

			h = 0
			aa = a.split(//)
			ba = b.split(//)

			aa.each.with_index { | ltr, i |
				h += 1 if ltr != ba.at(i) 
			}

			return h
		end
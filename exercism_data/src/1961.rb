def compute(x, y)

		x = x.chars
		y = y.chars

		x.zip(y).count { |x, y| x != y }

	end
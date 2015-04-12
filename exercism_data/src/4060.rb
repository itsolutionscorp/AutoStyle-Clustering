def compute(x, y)

		x = x.chars
		y = y.chars

		count = 0

		x.zip(y).each { |x, y| x != y ? count += 1 : count}

		count

	end
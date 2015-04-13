def compute(a, b)

		arrayb = b.split('')
		arraya = a.split('')
		total = 0
		myval = 0

		arrayb.each_index do |i|
			myval = arrayb[i] <=> arraya[i]
			total += myval.abs
		end

		total

  end
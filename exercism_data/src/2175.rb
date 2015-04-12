def compute(string_a, string_b)
        amount = 0
		0.upto(string_a.length) do |i|
			if string_a[i] && string_b[i]
				amount += 1 unless string_a[i] == string_b[i]
			end
		end
		amount
    end
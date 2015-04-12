def compute(a, b)
		a.each_codepoint.zip(b.each_codepoint).select {|l, r| l != r}.length
	end
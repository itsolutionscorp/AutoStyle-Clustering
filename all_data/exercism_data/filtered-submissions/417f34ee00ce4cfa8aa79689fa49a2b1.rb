def compute (arg1, arg2)
		(0...[arg1.length, arg2.length].min).count { |i| arg1[i] != arg2[i]	 }
	end
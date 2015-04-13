def compute(data1, data2)

		data1.chars.zip(data2.chars).select{|x| x[0] != x[1]}.count
	end
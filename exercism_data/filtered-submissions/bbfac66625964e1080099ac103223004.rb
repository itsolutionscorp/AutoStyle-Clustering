def compute(data1, data2)

		(0...data1.length).count{|x| data1[x] != data2[x]}
	end
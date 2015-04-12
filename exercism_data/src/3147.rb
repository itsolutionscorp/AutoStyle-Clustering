def compute(data1, data2)
		# inspired by mkummer at http://exercism.io/submissions/50f20884403a4cae87189adfc9a0fb07
		(0...data1.length).count{|x| data1[x] != data2[x]}
	end
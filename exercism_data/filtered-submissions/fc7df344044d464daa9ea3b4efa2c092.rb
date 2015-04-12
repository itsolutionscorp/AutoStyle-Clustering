def compute(p1, p2)
			p1.split('').zip(p2.split('')).map{|x| (x[0] == x[1]) ? 0 : 1}.reduce(:+)
		end
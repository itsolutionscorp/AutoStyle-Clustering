def compute(a,b)
			hamming = 0
			h = a.split('').zip b.split('')
			h.each { |a,b| hamming +=1 unless a == b }

			hamming
		end
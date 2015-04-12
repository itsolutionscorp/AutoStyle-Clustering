def compute(a,b)
			aa = a.split('')
			bb = b.split('')
			h = aa.zip bb
			hamming = 0
			h.each do |a,b|
			hamming +=1 unless a == b
				
			

			end
			hamming
		end
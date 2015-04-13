def compute(a, b)
		counter = 0
		hammingDistance = 0
		if !a.eql? b
			while counter < a.length && counter < b.length
				if a[counter] != b[counter]
					hammingDistance +=1
				end
				counter +=1
			end
		end
		hammingDistance
	end
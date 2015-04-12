def compute(a,b)
		tol = [a.length, b.length].min() -1
		sum = (0..tol).reduce(0) { |sum,i|
			if (a[i]!=b[i])	
			then
				sum += 1
			end
			sum
		}
		
	end
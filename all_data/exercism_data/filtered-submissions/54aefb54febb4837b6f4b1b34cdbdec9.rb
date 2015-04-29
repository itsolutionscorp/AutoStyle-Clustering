def compute ( a, b )
		count = 0
		for i in 0 .. [a.length() , b.length()].min - 1
			if(a[i] != b[i])
				count = count + 1
			end
		end
		count
  	end
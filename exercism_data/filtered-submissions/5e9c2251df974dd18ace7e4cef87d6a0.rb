def compute(arg0, arg1)
		total = 0
		for place in (0...[arg0.length, arg1.length].min)
			if arg0[place] != arg1[place]
				total +=1
			end
		end
		total
	end
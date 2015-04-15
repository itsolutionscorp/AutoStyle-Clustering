def compute(firstStr,secondStr)
		i=0
		sum=0
		while i<firstStr.length
			if firstStr[i]!=secondStr[i]
				sum=sum+1
			end
			i=i+1
		end
		return sum
	end
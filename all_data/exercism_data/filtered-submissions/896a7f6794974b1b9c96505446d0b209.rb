def compute (x,y)
	mismatch=0
	if x!=y
		if x.length == y.length
			xArray=x.split("")
			yArray=y.split("")
			c=0
			while c < x.length
				if xArray[c]!=yArray[c]
					mismatch +=1
				end
				c +=1
			end

		else
			puts "Error! String length mismatch!"
		return 1
	end
	end
	puts mismatch
	return mismatch
end
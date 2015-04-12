class Hamming
	def Hamming.compute(h1 , h2)
		if (h1.length <  h2.length)
			i = h1.length
		else
			i = h2.length
		end
		dist = 0
		(0 .. i-1 ).each do |x|
			if (h1[x] != h2[x])
				dist += 1
			end
		end
		return dist
	end
end

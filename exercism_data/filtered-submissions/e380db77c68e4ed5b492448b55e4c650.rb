module Hamming

	def Hamming.compute(s,t)
		ret	= 0;

		if ( s.length > t.length )
			lenghtMax = t.length
		else
			lenghtMax = s.length
		end

		for i in 0...lenghtMax
			if ( s[i] != t[i] )
				ret += 1
			end
		end

		return ret
	end

end

def compute(pStr, sStr)

		if ( pStr.length === sStr.length ) then
		    for i in 0..(sStr.length-1) do
				resp = 1 + resp.to_i if ( pStr[i] != sStr[i])
		    end
		    
		end
		resp.to_i
    end
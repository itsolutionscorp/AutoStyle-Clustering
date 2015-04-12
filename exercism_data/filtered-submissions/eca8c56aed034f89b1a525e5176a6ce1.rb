def compute(a,b)
		hammingCount= 0
		compareCount= [ a.length,b.length ].min
		( 0 .. compareCount ).each { |indx|
			if a[indx] != b[indx] then
				hammingCount+= 1
			end
		}
		hammingCount
	end
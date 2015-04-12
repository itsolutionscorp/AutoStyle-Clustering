class Hamming

	def compute(a,b)
		if a.length != b.length then
			return nil
		end
	
		count= 0
		( 0 .. a.length ).each { |indx|
			if a[indx] != b[indx] then
				count= count + 1
			end
		}
		count
	end
end

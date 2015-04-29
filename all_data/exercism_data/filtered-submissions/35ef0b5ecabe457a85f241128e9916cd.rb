def Hamming (string_a, string_b)
	distance = 0
	if string_a.length < string_b.length || string_a.length == string_b.length
		for i in 0..(string_a.length - 1)
			if string_a[i] != string_b[i]
				distance += 1
			end
		end
	else
		for i in 0..(string_b.length - 1)
			if string_a[i] != string_b[i]
				distance += 1
			end
		end
	end
	distance
end

	
		

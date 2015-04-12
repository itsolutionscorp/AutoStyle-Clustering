def compute(a, b)
	  a_array = a.split('')
		b_array = b.split('')
		sum = 0

		if a_array.length > b_array.length
			a_array.pop
		end

		result = a_array.zip(b_array).map! { |x, y| x == y }

	  result.each do |val|
	 		if val == false
    		sum += 1
    	end
    end
   sum
	end
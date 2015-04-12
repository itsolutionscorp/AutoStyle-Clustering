def compute(val1, val2)
		distance = 0
    val1.each_char.with_index do |n,i|
      distance += 1 if n != val2[i]
    end
    distance
	end
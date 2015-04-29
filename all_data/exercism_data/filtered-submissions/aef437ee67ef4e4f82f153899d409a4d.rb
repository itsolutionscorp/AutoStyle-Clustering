def compute (x, y)
	  smallest_one = x.size < y.size ? x.size : y.size
		arr_x = x.split('')
		arr_y = y.split('')
		@count = 0
		smallest_one.times do |i|
			@count += 1 if arr_x[i] != arr_y[i]
		end
		return @count
	end
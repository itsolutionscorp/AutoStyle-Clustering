def compute(a, b)
		velocity = 0
		for i in 0..a.length
			if(a[i] != b[i])
				velocity += 1
			end
		end
		return velocity
	end
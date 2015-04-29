def compute(a, b)
  		a.chars.zip(b.chars).count { |a| a[0] != a[1] && a[1] }
  	end
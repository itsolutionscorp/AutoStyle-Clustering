def compute(a, b)
  		diffCount = 0
  		(0...[a.length,b.length].min).each do |i|
  			if a[i] != b[i]
  				diffCount = diffCount + 1
  			end
  		end
  		return diffCount
  	end
def compute(base,test)
  	hamming_diff = 0

  	[base.size,test.size].min.times do |i|
  	  hamming_diff += 1 if base[i] != test[i]
  	 end
  	hamming_diff
  end
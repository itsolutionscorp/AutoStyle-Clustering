def compute(a,b)
  	finalcount=0;
  	count=[a.length,b.length].min.times do |i|
  		if(a[i] != b[i])
				finalcount+=1;
  		end
		end
  	return finalcount;
  end
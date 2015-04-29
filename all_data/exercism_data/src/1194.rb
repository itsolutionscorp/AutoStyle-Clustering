def compute(strandx, strandy)
		index_length = ([strandx.size, strandy.size].min) -1
		(0..index_length).inject(0) {|sum, i|  strandx[i] != strandy[i] ? sum +=1 : sum  }
	end
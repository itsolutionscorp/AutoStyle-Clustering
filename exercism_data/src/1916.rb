def compute(gene1,gene2)
		distance=0
		gene1.each_char.with_index{|elem,index|
		 if elem!=gene2[index] then distance += 1 end
		 }
		return distance
	end
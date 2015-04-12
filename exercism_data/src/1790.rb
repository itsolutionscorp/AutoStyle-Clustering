def compute(strand1, strand2)
		mutations = 0
    	strand1.chars.take(strand2.length).each_with_index do |genome1, index|
    		genome2 = strand2[index]
      		mutations += 1 unless genome1 == genome2
    	end
    	mutations
	end
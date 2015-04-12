module Hamming
	def Hamming.compute(dna1,dna2)
    	index = 0
    	hamming_distance = 0
    	while index < dna1.length and index < dna2.length
    		if dna1[index] != dna2[index]
    			hamming_distance += 1
    		end
    		index += 1
    	end
    	return hamming_distance
	end
end

=begin
x = "ATTTA"
y = "GTCTAQ"
puts Hamming.compute(x,y)
=end

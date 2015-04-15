class Hamming

def self.compute(firstrand, secondstrand)
 	if firstrand.length > secondstrand.length
 		strandlength = secondstrand.length
 	else secondSL > firstSL
 		strandlength = firstrand.length
 	end
 	hdistance = 0
 	i = 0
	while i < strandlength
 		if firstrand[i] != secondstrand[i]
 			hdistance += 1
 		end
 		i += 1
 	end
 	return hdistance
 end
 end

class Hamming
	def self.compute(dna1,dna2)
		if dna1.length > dna2.length
			return 1
		elsif dna2.length > dna1.length
		 	return 2
		end
		cnt = 0
		dna1.each_char.to_a.each_with_index do |char,index|
          if char != dna2[index]
          	cnt+=1
          end
		end
		cnt
	end
end
#

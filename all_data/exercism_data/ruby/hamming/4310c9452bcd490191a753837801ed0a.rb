class Hamming
	def initialize
	end
	
	def self.compute(l1, l2)
		l1_split = l1.split("")
		l2_split = l2.split("") 
		hamming_score = 0
		l1_split.each_with_index do |char, i|
			return hamming_score if !l2_split[i] 	
			char == l2_split[i] ? next : hamming_score+=1	
		end	
		hamming_score
	end
end

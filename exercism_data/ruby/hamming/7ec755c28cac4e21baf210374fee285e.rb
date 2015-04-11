class Hamming
	def self.compute(sequence1, sequence2)
		mutations = 0
		(0..shortest_sequence(sequence1, sequence2) - 1).each do |x|
		  if sequence1[x] != sequence2[x] 
		  	mutations += 1
		  end
		end 
		mutations
	end

	private
	def self.shortest_sequence(sequence1,sequence2)
		sequence1.size < sequence2.size ? sequence1.size : sequence2.size
	end
end

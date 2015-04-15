class Hamming
	def self.compute dna1, dna2
		return 0 if dna1.empty? or dna2.empty?
		h1,t1 = extract_head_and_tail(dna1)
		h2,t2 = extract_head_and_tail(dna2)
		mutation = (h1 != h2)? 1 : 0
		return mutation + compute(t1,t2)
	end

	def self.extract_head_and_tail s
		return s[0], s[1..-1]
	end
end

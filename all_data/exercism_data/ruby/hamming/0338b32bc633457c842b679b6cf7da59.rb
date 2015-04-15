class Hamming
	def self.compute dna_strand1, dna_strand2
		return 0 if dna_strand1.empty? or dna_strand2.empty?
		h1,t1 = extract_head_and_tail(dna_strand1)
		h2,t2 = extract_head_and_tail(dna_strand2)
		mutation = (h1 != h2)? 1 : 0
		mutation + compute(t1,t2)
	end

	def self.extract_head_and_tail s
		return s[0], s[1..-1]
	end
end

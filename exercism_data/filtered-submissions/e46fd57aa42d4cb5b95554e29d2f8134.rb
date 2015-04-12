class Hamming
	def compute(strand1, strand2)
		raise 'strands must be equal in length' if strand1.length != strand2.length
		hamming = 0
		(0..strand1.length).each do |i|
			hamming += 1 if strand1[i] != strand2[i]
		end
		hamming
	end
end

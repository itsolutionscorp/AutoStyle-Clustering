class Hamming
	def compute(strand1, strand2)
		strand1, strand2 = [strand1.chars, strand2.chars].sort_by { |x| x.length }
		strand1.count { |base| base != strand2.shift}
	end
end

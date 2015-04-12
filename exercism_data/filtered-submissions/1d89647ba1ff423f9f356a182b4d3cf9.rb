class Hamming
	# refactored the method based on discussion on philiplarsson's submission
	def compute(strand_1, strand_2)
		comparisons = [strand_1.length, strand_2.length].max
		comparisons.times.count { |i| strand_1[i] != strand_2[i] }
	end
end

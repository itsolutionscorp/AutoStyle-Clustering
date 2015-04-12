class Hamming
	def compute(strand1,strand2)
		hamming_diff = 0

		[strand1.length, strand2.length].min.times do |i|
			strand1[i] != strand2[i]? hamming_diff += 1 : nil
		end

		hamming_diff
	end
end

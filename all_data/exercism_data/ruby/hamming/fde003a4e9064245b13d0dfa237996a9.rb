class Hamming
	def self.compute(strand1,strand2)
		hamming_diff = 0

		[strand1.length, strand2.length].min.times do |i|
			if strand1[i] != strand2[i]
				then hamming_diff += 1
			end
		end

		return hamming_diff
	end
end

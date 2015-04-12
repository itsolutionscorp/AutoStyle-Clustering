class Hamming
	def compute(strand1, strand2)
		hamming_count,count = 0,0
		strand1.each_char do |c|
			break if strand2[count].nil?
			hamming_count += 1 if c != strand2[count]
			count += 1	
		end
		hamming_count
	end
end

def compute(strand1, strand2)
    count = 0
		[strand1.size, strand2.size].min.times do |i|
			count += 1 unless strand1[i] == strand2[i]
		end
    count
	end
end
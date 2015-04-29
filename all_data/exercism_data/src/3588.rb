def compute(strand1, strand2)
    (0...[strand1.size, strand2.size].min).map do |i|
			strand1[i] == strand2[i]
    end.count(false)
	end
end
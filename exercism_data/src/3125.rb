def compute(strand1, strand2)
		strand1.each_char.zip(strand2.each_char).count { |g| g[1] and g[0] != g[1] }
	end
end
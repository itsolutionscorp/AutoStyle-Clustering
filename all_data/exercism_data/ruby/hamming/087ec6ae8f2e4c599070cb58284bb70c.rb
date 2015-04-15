class Hamming

def self.compute(a, b)
	hamming = 0
	if (a == b) then
		return hamming	# Optimisation
	end
	(0..[a.length, b.length].min-1).each do |i|
		hamming+=1 if a[i] != b[i]
	end
	return hamming
end

end

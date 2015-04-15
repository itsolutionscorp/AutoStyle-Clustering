class Hamming

def self.compute(a, b)
	hamming = 0
	if (a == b) then
		return hamming	# Optimisation
	end
	a.each_char.zip(b.each_char) do |l, r|
		(l.nil? || r.nil?) || (l == r) || hamming += 1
	end
	hamming
end

end

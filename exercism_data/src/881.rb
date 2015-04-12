class Hamming

def compute(a, b)
	hamming = 0
	a = a.slice(0,[a.length, b.length].min)
	a.each_char.zip(b.each_char) do |l, r|
		(l == r) || hamming += 1
	end
	hamming
end

end

class Hamming
def initialize
end
def Hamming.compute(a,b)
	res = 0
	if b.size < a.size
		(0...b.size).each do |x|
			res += 1 if a[x]!=b[x]
		end
		return res
	end
	(0...a.size).each do |x|
		res += 1 if a[x]!=b[x]
	end
	res
end
end

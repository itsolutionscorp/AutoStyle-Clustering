class Hamming
def compute(string1, string2)
	diff = 0
	string1.chars.zip(string2.chars).count do | x, y |
	if x != y 
	diff += 1
	end
	end
	return diff
end
end

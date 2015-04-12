class Hamming
	def compute(string1, string2)
		string1.length == string2.length or raise "Input Strings should be the same length."
		distance = 0
		(0..string1.length-1).each do |i|
			distance += 1 if string1[i] != string2[i]
		end
		distance
	end
end

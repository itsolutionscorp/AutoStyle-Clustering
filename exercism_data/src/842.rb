class Hamming
	def compute(string1, string2)
		i = 0
		counter = 0
		until i >= string1.length do
			if string1[i] != string2[i]
				counter += 1
			end
			i += 1
		end
		return counter
	end
end

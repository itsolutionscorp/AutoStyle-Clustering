class Hamming
	def compute(data1, data2)
		# inspired by joelpickup solution at http://exercism.io/submissions/a98fe7561a214f09aceb8e9669d2edc5
		data1.chars.zip(data2.chars).select{|x| x[0] != x[1]}.count
	end
end

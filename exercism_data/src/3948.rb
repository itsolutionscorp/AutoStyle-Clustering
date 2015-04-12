class Hamming
	public
	def compute(str1, str2)
		str1.chars.zip(str2.chars).reduce(0) { |difference, arr |
			difference += 1 if arr[0] != nil && arr[1] != nil && arr[0] != arr[1]
			difference
		}
	end
end

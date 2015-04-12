class Hamming
	def compute(arg1, arg2)
		distance = 0
		arg1.chars.take(arg2.length).zip arg2.chars do |arr|
			distance += 1 if arr[0] != arr[1]
		end
		distance
	end
end

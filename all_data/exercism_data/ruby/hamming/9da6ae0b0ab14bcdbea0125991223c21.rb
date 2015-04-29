class Hamming

	def self.compute(arg1, arg2)
		array1 = arg1.split(//)
		array2 = arg2.split(//)
		outputs = []
		super_array = array1.zip(array2)

		super_array.each do |set|
			if set.first == set.last
				outputs << 0
			else
				outputs << 1
			end
		end

		sum = 0
		outputs.each do |i|
			sum += i
		end

		sum
	end

end

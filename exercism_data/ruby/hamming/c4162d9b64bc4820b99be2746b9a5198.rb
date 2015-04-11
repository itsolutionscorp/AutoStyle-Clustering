class Hamming
	def self.compute(orig, copy)
		counter = 0

		orig.split("").each_with_index do |char, index|
			charAt = copy[index]
			counter += 1 if charAt != char and index < copy.size
		end

		counter
	end
end

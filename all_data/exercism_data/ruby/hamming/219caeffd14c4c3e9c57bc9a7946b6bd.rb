class Hamming

	def self.compute(a, b)
		difference = 0
		minlength = [a.length, b.length].min
		(0..minlength - 1).each do |i|
			difference += 1 unless a[i] == b[i]
		end
		difference
	end
end

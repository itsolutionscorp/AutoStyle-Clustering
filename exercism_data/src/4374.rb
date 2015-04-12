class Hamming

	def compute one, another
		size = [one.size, another.size].min
		(0...size).count do |i|
			one[i] != another[i]
		end
	end
	
end

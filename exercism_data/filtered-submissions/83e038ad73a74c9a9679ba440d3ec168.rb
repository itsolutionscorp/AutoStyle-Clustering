class Hamming

	def compute(a,b)
		raise "lengths do not match" if a.length != b.length 				
		zipped = a.chars.zip(b.chars)		
		return zipped.select{ |(a,b)| a != b }.count				
	end
end

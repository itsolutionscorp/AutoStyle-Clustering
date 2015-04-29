class Hamming

	def self.compute(a,b)
		raise "lengths do not match" if a.length != b.length || a.length == 0 				
		diffs = 0		
		zipped = a.split('').zip(b.split(''))
		zipped.each do |(a,b) |
			if a != b
			diffs = diffs +1
			end
		end
		return diffs		
	end
end

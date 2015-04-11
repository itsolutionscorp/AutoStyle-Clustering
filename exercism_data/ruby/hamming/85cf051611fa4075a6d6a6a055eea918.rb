class Hamming
	include Enumerable
	def self.compute(ary1, ary2)
		arys = self.zip_by_longest(ary1,ary2)
		hamming_difference = 0
		arys.each do |pair|
			hamming_difference += 1 if pair[0] != pair[1]
		end
		hamming_difference
	end
	def self.zip_by_longest(ary1,ary2)
		if ary1.length > ary2.length
			long_ary, short_ary = ary1, ary2
		else
			long_ary, short_ary = ary2, ary1
		end
		long_ary.chars.zip(short_ary.chars)
	end
end

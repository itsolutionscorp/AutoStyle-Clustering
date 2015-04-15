class Hamming
	def self.compute(ary1, ary2)
		arys = zip_by_longest(ary1,ary2)
		arys.reduce(0) { | difference, p| difference + (p[0] != p[1] ? 1 : 0) }
	end
	private
	def self.zip_by_longest(ary1,ary2)
		if ary1.length > ary2.length
			long_ary, short_ary = ary1, ary2
		else
			long_ary, short_ary = ary2, ary1
		end
		long_ary.chars.zip(short_ary.chars)
	end
end

class Hamming
	def self.compute(ary1, ary2)
		arys = zip_by_longest(ary1,ary2)
		arys.reduce(0) { | difference, p| difference + string_equal(p[0],p[1]) }
	end
	private

	def self.string_equal(p,q)
		p != q ? 1 : 0
	end

	def self.zip_by_longest(ary1,ary2)
		arys = if (ary1.length > ary2.length)
			[ary1, ary2]
		else
			[ary2, ary1]
		end
		arys[0].chars.zip(arys[1].chars)
	end
end

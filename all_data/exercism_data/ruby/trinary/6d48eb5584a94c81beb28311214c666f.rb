class Trinary
	def initialize(s)
		# only keep s if it is all trinary.
		@ts = s.delete("012") == "" ? s : ""
	end
	def to_decimal()
		d = 0
		@ts.each_byte do |b|
			d = d*3+b-48
		end
		d
	end
end

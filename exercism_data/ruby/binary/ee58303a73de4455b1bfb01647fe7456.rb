class Binary
	
	def initialize(bits)
		@bits = bits =~ /^[01]+$/ ? arrayify(bits) : [0]
	end

	def to_decimal
		@bits.each_with_index.map { |b, i| 
			b*2**(@bits.size-i-1)
		}.reduce(:+)
	end

	private

		def arrayify(string)
			string.split('').map { |bit| bit.to_i }
		end
end

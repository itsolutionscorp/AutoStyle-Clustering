class Luhn

	def initialize(n)
		@n = n.to_s.chars.map(&:to_i)
	end

	def addends
		@n.each.with_index.map do |n, i|
			n = ( (@n.length - i + 1) % 2 == 1 ? 2*n : n )
			n > 9 ? n - 9 : n
		end
	end

	def checksum
		addends.reduce(:+)
	end

	def valid?
		checksum % 10 == 0
	end

	def self.create(n)
		bit = (10 - (Luhn.new(n*10).checksum % 10)) % 10
		n*10 + bit
	end

end

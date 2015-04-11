class Grains
	def initialize
		@@squares ||= Array.new(64, nil)
		@@squares[0] ||= 1
	end

	def square n
		fail ArgumentError if (n < 1) || (n > 64)
		return @@squares[n - 1] if @@squares[n - 1]
		@@squares[n - 1] = (square(n - 1)) * 2
	end

	def squares
		@@squares
	end

	def total
		square 64 if @@squares.include? nil
		@@squares.reduce(&:+)
	end
end

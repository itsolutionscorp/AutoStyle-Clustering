class Grains
	def initialize
	end

	def square(num)
		num == 1 ? 1 : square(num - 1) * 2
	end

	def total
		(1..64).inject{ |sum, n| sum + square(n)}
	end
end

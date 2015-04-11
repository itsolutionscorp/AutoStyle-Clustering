class	Grains
	def square(num)
		2 ** (num - 1)
	end

	def total
		(1..64).map{ |n| square(n) }.inject(:+)
	end
end

Grains.new.total

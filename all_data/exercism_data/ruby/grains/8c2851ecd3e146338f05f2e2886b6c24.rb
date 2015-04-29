class Grains
	def square(num)
		1 << (num - 1)
	end

	def total(num=64)
		(1 << num) - 1
	end
end

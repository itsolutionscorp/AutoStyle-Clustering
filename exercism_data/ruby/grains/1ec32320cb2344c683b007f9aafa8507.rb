class Grains
	def square(num)
		(1..num).inject {|result, element| 2*result}
	end

	def total
		(1..64).inject {|result, element| result + square(element)}
	end
end

SQUARES_CONST = 64
class Grains
	def calculate ()
		i = 1
		result = 0
		begin
			result += square(i)
		end while i<SQUARES_CONST
		return result
	end
	def square (value)
		return Math.sqrt(value)
	end
end

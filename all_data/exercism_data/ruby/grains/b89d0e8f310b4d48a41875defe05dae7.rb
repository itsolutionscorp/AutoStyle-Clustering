class Grains
	def square(pos)
		1 << (pos - 1)
	end
  def total
		result = 0
		(1..64).each do |i|
			result += square(i)
		end
		result
	end
end

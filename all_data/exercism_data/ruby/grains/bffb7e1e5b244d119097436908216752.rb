class Grains
	def square(num)
		@x = 1
		(num-1).times do
			(@x += @x)
		end
		return @x
	end
	def total
		total = []
		whoa = (1..64).to_a
		whoa.each do |x|
			total.push(square(x)) 
		end
		return total.reduce(:+)
	end
end

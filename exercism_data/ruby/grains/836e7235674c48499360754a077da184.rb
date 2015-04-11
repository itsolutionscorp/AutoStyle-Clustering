class Grains
	def square(num)

		sum = 0

		1.upto(num) do 
			if sum == 0
				sum = 1
			else
				sum = sum*2
			end
		end

		sum

	end

	def total
		sum = 0
		1.upto(64) do |x|
			sum = sum + square(x)
		end
		
		sum
	end
end

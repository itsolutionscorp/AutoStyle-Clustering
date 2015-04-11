class Grains
	def square(square)
		@square_hash = { 1 => 1 }
		(1..square).each do |x|
			unless x == 1
				@square_hash[x] = 2 * @square_hash[x-1]
			end
		end
		@square_hash[square]
	end

	def total
		sum = 0
		(1..64).each do |x|
			sum += square(x)
		end
		sum
	end
end

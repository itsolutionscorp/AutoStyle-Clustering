class Grains
	def square(n)
		return 2**(n-1)
	end

	def total
		@total=0
		for i in 1..64
			@total += square(i)
		end
#		puts "#{@total}"
		return @total
	end
end

#grains = Grains.new()
#grains.total
#puts grains.square(16)

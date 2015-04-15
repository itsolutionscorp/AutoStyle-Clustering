class Grains
	#def initialize

	#end

	def square(num)
		numGrains = 1
		if num > 1
			(2..num).each do 
				numGrains = numGrains*2
			end
			return numGrains
		else
			return numGrains
		end
	end

	def total
		totalGrains = 0
		(1..64).each do |i|
			totalGrains += self.square(i)
		end
		return totalGrains
	end
end

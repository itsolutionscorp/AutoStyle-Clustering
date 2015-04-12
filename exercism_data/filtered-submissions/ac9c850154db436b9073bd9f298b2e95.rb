class Hamming
	def compute(input1, input2)
		@count = 0
		@max = 0
		@start = 0
		if input1.length <= input2.length
			@max = input1.length
		else
			@max = input2.length
		end

		dna1 = input1.split('')
		dna2 = input2.split('')

		while @start < @max 
			if dna1[@start] != dna2[@start]
				@count += 1
			end
			@start+=1
		end
		@count
	end
end

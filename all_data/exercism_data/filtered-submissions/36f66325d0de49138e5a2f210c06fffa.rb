class Hamming
	def Hamming.compute(val1, val2)
		@val1 = val1
		@val2 = val2
		@size = 0
		if @val1.length<= @val2.length
			@len = @val1.length
		else
			@len = @val2.length
		end
		for i in 0..@len-1
			if !(@val1[i].eql?(@val2[i])) 
				@size+=1
			end
		end
		return @size
	end
end

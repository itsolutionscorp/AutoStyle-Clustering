class Grains
	@@squares=[nil]*64
	def square(x)
		@@squares[x] ||= 2**(x-2)*2
	end
	def total
		@@squares.each.inject{|sum,x|sum+square(x)}
	end
end

class Grains
	@@square=[nil]*64
	def square(x)
		@@square[x] ||= 2**(x-2)*2
	end
	def total
		(1..64).inject{|sum,x|sum+square(x)}
	end
end

class Grains

	def square(number)
		if number < 1 || number > 64 then 
			'Not a valid argument.'
		else
			2 ** (number -1)
		end
	end
	
	def total
		t = 0
		for i in 1..64
			t += square(i)
		end
		t
	end
	
end

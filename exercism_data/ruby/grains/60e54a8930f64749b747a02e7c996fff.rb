class Grains

	def square(number)
		if number == 1 then 1
		elsif number < 1 || number > 64 then 'Not a valid argument.'
		else
		2 ** (number -1)
		end
	end
	
	def total
		t =1
		for i in 1..63
			t += 2 ** i
		end
		t
	end

end

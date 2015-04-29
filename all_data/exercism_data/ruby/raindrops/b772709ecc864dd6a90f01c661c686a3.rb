class Raindrops
	def convert(num)
		acc = " "
		if num % 3 == 0
			return acc+='Pling'
		end
		if num % 5 == 0
			return  acc+='Plang'			
		end
		if num % 7 == 0
			return acc+='Plong'
		end
	else
		return num.to_s

	end
end

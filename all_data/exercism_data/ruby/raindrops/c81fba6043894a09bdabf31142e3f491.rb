class Raindrops
	def convert(number)
		if number==1
			return "1"
		else	
			divisores=isPrime(number)
			output = setResult(divisores)	
			# If the result not contain the (3,5,7) in the array of factors, return the same number without any modification
			if (output=="")
				return number.to_s
			end	
		end	
		return output
	end		
	# This method return all factors of a number in an ARRAY
	def isPrime(number)
		divisores=[]
		1.upto(number){|num| 
			if (number % num == 0 )
				divisores.push num
			end		
		}
		return divisores
	end	
	# This method forms the final string based on the factors
	def setResult(divisores)		
		cadena=""
		divisores.each do |num|		
			case num
				when 3
				  cadena = cadena.concat("Pling")
				when 5
				  cadena = cadena.concat("Plang")
				when 7  	
				cadena = cadena.concat("Plong")				
			end				
		end
		return cadena
	end	
end

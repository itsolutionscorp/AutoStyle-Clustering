class Hamming

	def initialize
	end

	def self.compute (cadena1,cadena2)
		
		distancia=0
		
		# Verificamos si longitud de cadena1 es mayor a la 2, de ser asi, solo comparamos con la longitud de cadena2
		if cadena1.length > cadena2.length
			longitud=cadena2.length
		else
			longitud=cadena1.length
		end

		for i in 0..longitud-1
			if cadena1[i]!=cadena2[i]
				distancia+=1
			end
		end
		
		return distancia

	end
	
end

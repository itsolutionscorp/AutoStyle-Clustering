# require 'Math'

class Raindrops

	def convert(numero)
		
		original=numero									 # Conservamos el numero original en caso de no encontrar factores primos 3,5,7
		salida=""										 #  
		raindropSpeak={3=>'Pling',5=>'Plang',7=>'Plong'} # Salida segun factor encontrado
		raindropsFound=Hash.new  						 # Aqui iremos guardando los factores encontrados y no duplicaremos
		serieImpar=3

		while numero %2 ==0
			numero=numero/2 
		end

		raiz=Math.sqrt(numero)
        # Raiz cuadrada de 5 es 2.23606797749979
		while (serieImpar<=raiz && numero > 1)

			if(numero % serieImpar==0)

				# puts serieImpar

				if raindropSpeak.has_key?(serieImpar) && raindropsFound.has_key?(serieImpar)==false
					
					raindropsFound[serieImpar]=serieImpar
					salida=salida+raindropSpeak[serieImpar]

				end

				numero=numero/serieImpar
			else
				serieImpar=serieImpar+2
			end

		end

		# Si seriaImpar existe como llave en hash 3,5,7 y no existe aun encontrado en raindropsfound

		# puts "ultimo numero #{numero}"
		
		if(numero==3 || numero==5 || numero==7)
			serieImpar=numero
			if (raindropSpeak.has_key?(serieImpar) && !raindropsFound.has_key?(serieImpar) ) then
			
				raindropsFound[serieImpar]=serieImpar
				salida=salida+raindropSpeak[serieImpar]
		
			end
		else

			if salida==""
				salida=original.to_s
			end

		end


		# puts salida 

		return salida

	end

end

# o=Raindrops.new
# puts o.convert(21)

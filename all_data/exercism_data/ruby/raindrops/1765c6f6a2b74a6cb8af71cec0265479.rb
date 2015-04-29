class Raindrops
	def convert(num)
		cadena= ''
		if num.modulo(3)==0
			cadena+='Pling'
		end
		if num.modulo(5)==0
			cadena+='Plang'
		end
		if num.modulo(7)==0
			cadena+='Plong'
		else cadena+=num.to_s
		end
	end
end

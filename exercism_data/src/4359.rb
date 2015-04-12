class Hamming
	def compute(cad, cad2)
		retorno = 0
		if cad.length == cad2.length
			i = 0
			while i<cad.length
				if !(cad[i].eql?(cad2[i]))
					retorno += 1
				end
				i=i+1
			end
		end
		return retorno
	end
end

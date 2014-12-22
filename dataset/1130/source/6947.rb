#tarea de anagramas
def combine_anagrams(words)
	conjunto=Array.new	
	k=0
	i=0	
	while i<words.size
		
		aux=Array.new
		l=0
		
		val=words[i]
		aux[l]=words[i]
		
		words.delete_at(0)

		j=0
		while j<words.size
			if val.length==words[j].length
				if (isEqual(trans(val),trans(words[j])))
					l+=1
					aux[l]=words[j] 
					words.delete_at(j)
				else
					j+=1	
				end
			else
				j+=1
			end

		end

		conjunto[k]=aux
		k+=1
	end	
	
	return conjunto		
end

def isEqual(real,aux)
	i=0
	isAna=true
	while ((i<aux.size) && (isAna==true))
		j=0
		encontro=false
		while ((encontro==false) && (j<real.size))
			if aux[i]==real[j]
				real.delete_at(j)
				encontro=true
			end
			j+=1
		end
		
		if (encontro==false)
			isAna=false			
		end
		i+=1
	end	
	
	return isAna
end

def trans(word)
	word=word.downcase
	arreglo=Array.new
	i=0
	word.each_char {|c| arreglo[i]=c;i+=1}
	return arreglo
end

#trans("HEllo")

#combine_anagrams(['Cars','for','potatoes','rAcs','four','scar','creams','scream'])
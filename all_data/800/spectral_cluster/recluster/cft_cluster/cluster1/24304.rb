def ordenar_cadena(cadena,cases)
    if(cases==0)
      cadena = cadena.chars.sort.join
    else
      cadena = cadena.chars.sort { |a, b| a.casecmp(b) } .join
    end
   cadena
end


def combine_anagrams(palabras)
  i=0;k=0;l=0;j=0
arreglo = Array.new
arreglo[l]=Array.new


if(palabras.length==0)
  return palabras
end  
while(i<palabras.length)
  palabraInicial = ordenar_cadena(palabras[i],1)
  palabraComparar = ordenar_cadena(palabras[k],1)
  if(palabraInicial.casecmp(palabraComparar)==0)
    arreglo[l][j]=palabras[k]
    j+=1
  end
  
  k+=1

  if(k==palabras.length)
    i+=1 ;  k=0; l=l+1; j=0
    if(i!=palabras.length)
      arreglo[l]=Array.new
    end
  end
end 

return arreglo.uniq
end

#words = ["oidoosa",'Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream','situation','situation']

words=['C']
print combine_anagrams(words)






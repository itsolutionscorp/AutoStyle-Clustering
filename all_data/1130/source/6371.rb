def combine_anagrams(words)
  
    if(words!=nil) #comprobar que el vector no tenga longitud cero
      if(words.size!=0)
      if(words.size==1)
        return words
      else
          palabras=Array.new
          pala=Array.new
          salida=Array.new
          resultado=Array.new
          
          palabras=words.clone();
    
          palabras.each_with_index do |pal,i| #ccojer cada palabra del vector Palabras
            pal=pal.downcase;
            pala= pal.to_s.split(//) #volver cada palabra un vector
            pala=pala.sort(); #organizar el vector en orden alfabetico 
            salida[i]=pala.to_s() #convertir el vector en palabra, cambiar todo a minusculas y almacenar en vector auxiliar llamado SALIDA
          end
                 
          for i in 0...salida.size         
            if(salida[i]!=0)
              resultado[i]=Array.new;
              resultado[i][0]=words[i]; #la primera posiciòn del vector debe ser la palabra encontrada
              p=1; #la siguiente posicion del vector
              for j in 0...salida.size                         
                if(j>=i)              
                  if(salida[i]==salida[j+1])
                    resultado[i][p]=words[j+1]
                    salida[j+1]=0;
                    p=p+1;              
                  end
                end
              end       
             end 
            end
     
          end
          
          resultado.each_with_index do |word,i|             
                 resultado.delete_at(i) if word==nil
              end 
          resultado.each_with_index do |word,i|             
                 resultado.delete_at(i) if word==nil
              end 
resultado.each_with_index do |word,i|             
              resultado.delete_at(i) if word==nil
           end 
resultado.each_with_index do |word,i|             
              resultado.delete_at(i) if word==nil
           end 
         #puts resultado.inspect()     
           # resultado.each_with_index do |word,i|             
            #     word.uniq!
             # end 
       
          return resultado;
     end
   else
    return words;
end
return words;
end


#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream'] 
#words =["HeLLo", "hello",'AAA','aaa','LLoHe']
#words=['Cars', 'for', 'Potatoes', 'racs', 'Four','SCAR', 'creams', 'scream']
#words=['a', 'BbB', 'BB', 'B', 'a','AAa', 'Aa', 'A']
words= ['a', 'a', 'a', 'A', 'b', 'b', 'c', 'D', 'd'] 
x=combine_anagrams(words)
#puts x;
puts x.inspect();


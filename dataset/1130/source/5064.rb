def combine_anagrams(words)
    numero=1
    resul=Array.new
    resul[0]=Array.new
    resul[0][0]="ahlo"
  words.each do |palabra|
      #puts("cojo"+palabra)
      palabra2=palabra.downcase
      ordenada=palabra2.split('').sort.join
      # resul.each do |grupo|
      
    
      encontrada=-1
      #puts("numero="+numero.to_s)
      for i in 0..numero-1 
          # puts("AQUIIIIII"+i.to_s)
          #  puts(resul[i][0])
          if(resul[i][0]==ordenada)
              # puts("encontrada")
              resul[i].push(palabra)
              encontrada=i
           end 
        end #while    
              
              
            if (encontrada==-1)
                # puts("no encontrada")
              resul[numero]=Array.new
              resul[numero].push(ordenada)
              resul[numero].push(palabra)
              numero=numero+1
            end #if
            
      
      end #words.each 
    #resul.delete_at(0)
    for i in 0..numero-1 do
        resul[i].delete_at(0)
        end
        resul.delete_at(0)
    
    #puts("numero ="+ numero.to_s)
    return(resul)
end #metodo

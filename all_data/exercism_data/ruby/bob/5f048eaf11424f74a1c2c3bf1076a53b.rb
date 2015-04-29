class Bob


 def hey(sentencia)
  
   if sentencia.strip.empty?      #Si sentencia esta vacia
      return 'Fine. Be that way!'   
   elsif sentencia =~ /[A-Z]/ && sentencia.upcase == sentencia #Si se compone de alfabeto y son mayusculas...
      return 'Woah, chill out!' 
   elsif sentencia.end_with?('?') #Si sentencia termina con ?
      return 'Sure.'
   else                           #Para todo lo demas....
      return 'Whatever.'
   end
 end

end

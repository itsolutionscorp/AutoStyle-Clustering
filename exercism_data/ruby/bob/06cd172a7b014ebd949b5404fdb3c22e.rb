class Bob
  def hey(frase)
    if frase=~/[A-Z]/
      puts"Woah, chill out!"
    elsif frase.end_with?("?")
     puts "Sure"
     elsif frase.lstrip== ' '
       puts "Fine. Be that way!"
     elsif
       puts "wherever"
     end      
   end
 end

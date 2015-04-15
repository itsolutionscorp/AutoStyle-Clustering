class Bob
 def hey(dice)    
    if dice.strip.empty?   #vacia
         return 'Fine. Be that way!'
    elsif dice.upcase==dice   # mayusculas 
        return 'Woah, chill out!'
    elsif dice.end_with?('?') #pregunta
        return 'Sure'
    else			#todo lo demas
        return 'Whatever.'
    end
 end
end

class Bob
  def hey(input)
    if input == input.upcase && input.index(/[a-zA-Z]/)!= nil # a shout is all the same as the upcased string but also has letters
      return 'Woah, chill out!'
    
    elsif input.strip == '' # strip all the whitespaces
      return 'Fine. Be that way!'  
    
    elsif  input.end_with?('?') # a question ends with a ?
      return 'Sure.'
    
    else  
      return 'Whatever.'    
    end
  end
end

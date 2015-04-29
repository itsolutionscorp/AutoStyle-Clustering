class Bob
  def hey(input)
    
    case 
    when silence?(input) 
     'Fine. Be that way!'
    when shouting?(input)
     'Woah, chill out!'
    when asks_a_question?(input)
     'Sure.'
    else 'Whatever.'
    end
    
  end


  def silence?(input)
    input == '' || input.chars.all? { |e|  e == ' '}
  end

  def shouting?(input)
    input == input.upcase 
  end

  def asks_a_question?(input)
    input.end_with?('?')
  end

end

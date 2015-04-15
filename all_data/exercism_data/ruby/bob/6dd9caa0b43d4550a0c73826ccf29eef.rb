class Bob

  def hey(string)
    
    if string.match(/  {2,}/)
      'Fine. Be that way!'
    elsif string.upcase == string && !string[0..-2].match(/\d/) && string != ''
      'Woah, chill out!'
    elsif string[-1] == '?'
      'Sure.'
    elsif string[-1] == '!' && string =~ /\d/   
      'Woah, chill out!'
    elsif string == ''  
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  
  end

end

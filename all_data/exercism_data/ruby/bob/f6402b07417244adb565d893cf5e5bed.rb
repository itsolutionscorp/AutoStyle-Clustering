class Bob

  def hey(string)
    if silent_treatment?(string)
      'Fine. Be that way!'
    elsif why_so_intense?(string)
      'Woah, chill out!'
    elsif you_askin_me?(string)
      'Sure.'
    else
      whatever(string)
    end      
  end


  def silent_treatment?(string)
    string.match(/  {2,}/) || string == ''
  end

  def why_so_intense?(string)
    (string[-1] == '!' && string =~ /\d/) || (string.upcase == string && !string[0..-2].match(/\d/) && string != '')
  end

  def you_askin_me?(string)
    string[-1] == '?'
  end

  def whatever(string)
    'Whatever.'
  end

end



# class Bob

#   def hey(string)
    
#     if string.match(/  {2,}/)
#       'Fine. Be that way!'
#     elsif string.upcase == string && !string[0..-2].match(/\d/) && string != ''
#       'Woah, chill out!'
#     elsif string[-1] == '?'
#       'Sure.'
#     elsif string[-1] == '!' && string =~ /\d/   
#       'Woah, chill out!'
#     elsif string == ''  
#       'Fine. Be that way!'
#     else
#       'Whatever.'
#     end
  
#   end

# end

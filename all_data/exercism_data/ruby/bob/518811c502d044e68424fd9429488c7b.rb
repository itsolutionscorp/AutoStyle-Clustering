class Bob
  def hey(input)
    case
    when shouting?(input)
      'Woah, chill out!'
    when question?(input)
      'Sure.'
    when silence?(input)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
  
  private
  
  def shouting?(input)
    has_uppercase_character?(input) && !has_lowercase_character?(input)
  end
  
  def question?(input)
    input.end_with?('?')
  end
  
  def silence?(input)
    input.strip.length == 0
  end
  
  def has_lowercase_character?(input)
    input.match(/\p{Lower}/)
  end
  
  def has_uppercase_character?(input)
    input.match(/\p{Upper}/)
  end
end

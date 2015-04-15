class Bob

  def hey( phrase )

    case 
    when phrase.strip.empty?
      'Fine. Be that way!'
    when phrase.upcase == phrase
      'Woah, chill out!'
    when phrase[-1,1] == '?'
      'Sure.'
    else
      'Whatever.'
    end

  end

end

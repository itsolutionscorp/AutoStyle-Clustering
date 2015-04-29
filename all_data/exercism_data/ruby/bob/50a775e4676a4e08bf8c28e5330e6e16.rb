class Bob 

  def hey(msg)
    
    only_whitespace = /\A\s*\Z/

    case msg 
      when nil, only_whitespace
        'Fine. Be that way!'      
      when msg.upcase
        'Woah, chill out!'
      when msg[0..-2] + '?'
        'Sure.'
      else 
        'Whatever.'
    end

  end

end

class Bob
  def hey(text)
    reply='Whatever.'
    if yelling?(text)
      reply='Woah, chill out!'
    end 
    if question?(text)
	  reply='Sure.'
    end
    if nothingToSay?(text)
      reply='Fine. Be that way!'
    end
    reply
  end

  def question?(text)
    text.end_with?('?') and not yelling?(text)
  end
  
  def yelling?(text)
    text==text.upcase and not text==text.downcase
  end
  
  def nothingToSay?(text)
   text.squeeze=='' or text.squeeze==' '
  end

end

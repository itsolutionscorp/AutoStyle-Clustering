class Bob

  def msg val=nil
    @message = val.strip unless val.nil?
    @message
  end

  def hey message
    msg message
    case 
    when silence?
      "Fine. Be that way!"
    when yelling?
      "Woah, chill out!"
    when question?
      "Sure."      
    else
      "Whatever."
    end      
  end

  def silence?
    msg.empty?
  end

  def yelling?
    msg.upcase == msg
  end

  def question?
    msg.end_with? "?"
  end

end

class Bob

  def hey message
      @message = message.strip
      puts @message
      case 
      when silence?
        return "Fine. Be that way!"
      when yelling?
        return "Woah, chill out!"
      when question?
        return "Sure."      
      else
        return "Whatever."
      end      
  end

  def silence?
    @message.empty?
  end

  def yelling?
    @message.upcase == @message
  end

  def question?
    @message.end_with? "?"
  end

end

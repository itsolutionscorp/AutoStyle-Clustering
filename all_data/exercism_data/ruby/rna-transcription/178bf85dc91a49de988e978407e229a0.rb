class Bob

  TELL_REPLY    = "Whatever."
  YELL_REPLY    = "Woah, chill out!"
  ASK_REPLY     = "Sure."
  SILENCE_REPLY = "Fine. Be that way."

  def hey(text)
    @text = text

    if silence?
      SILENCE_REPLY
    elsif yelling?
      YELL_REPLY
    elsif asking?
      ASK_REPLY   
    else
      TELL_REPLY
    end

  end

  private

  def silence?                  
    @text.to_s.empty?
  end

  def yelling?
    @text == @text.upcase
  end

  def asking?
    @text.end_with? '?'
  end

end

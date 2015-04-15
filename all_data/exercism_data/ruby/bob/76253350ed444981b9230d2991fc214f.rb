class Bob

  TELL_REPLY    = "Whatever."
  YELL_REPLY    = "Woah, chill out!"
  ASK_REPLY     = "Sure."
  SILENCE_REPLY = "Fine. Be that way."

  def hey(text)
    @text = text

    if silence?     # silence? first because other methods don't work on NilClass
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
    @text.nil? || @text.empty?  # .nil? first because .empty? does not work on NilClass
  end

  def yelling?
    @text == @text.upcase
  end

  def asking?
    @text.end_with? '?'
  end

end

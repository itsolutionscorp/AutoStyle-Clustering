class Bob
  RESPOND_TO_SILENCE = 'Fine. Be that way!'
  RESPOND_TO_SHOUTING = 'Woah, chill out!'
  RESPOND_TO_QUESTION = 'Sure.'
  RESPOND_TO_OTHER = 'Whatever.'

  def hey mesg
    if silence? mesg
      RESPOND_TO_SILENCE
    elsif shout? mesg
      RESPOND_TO_SHOUTING  
   elsif question? mesg
      RESPOND_TO_QUESTION
    else
      RESPOND_TO_OTHER
    end
  end

  def shout? mesg
    mesg == mesg.upcase
  end

  def question? mesg
    mesg[-1] == '?'
  end

  def silence? mesg
    mesg.strip.size == 0
  end
end

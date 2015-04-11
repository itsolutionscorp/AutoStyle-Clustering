class Bob
  def silence?
    @message.strip.size == 0
  end

  def question?
    @message.end_with? "?"
  end

  def shouting?
    @message.upcase == @message && @message =~ /[a-zA-Z]/
  end

  def hey(message)
    @message = message

    if shouting?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    elsif silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

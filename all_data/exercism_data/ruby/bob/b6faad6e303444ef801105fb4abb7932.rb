class Bob
  def hey message
    case
    when (silent? message)
      return 'Fine. Be that way!'
    when (shouting? message)
      return 'Woah, chill out!'
    when (question? message)
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end

  private

  def shouting? message
    if ((message =~ /\d/) == nil) || ((message =~ /[A-Z]/) != nil)
      return (message == message.upcase)
    end
  end

  def question? message
    return (message[-1] == '?')
  end

  def silent? message
    return (message.strip == '')
  end
end

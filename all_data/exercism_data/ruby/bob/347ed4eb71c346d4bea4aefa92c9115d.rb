class Bob
  def hey(message)
    case
    when silence?(message)
      'Fine. Be that way.'
    when question?(message)
      'Sure.'
    when shout?(message)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private
  def question?(message)
    message.end_with? '?'
  end

  def shout?(message)
    message.upcase.eql? message
  end

  def silence?(message)
    message.to_s.eql? ""
  end
end

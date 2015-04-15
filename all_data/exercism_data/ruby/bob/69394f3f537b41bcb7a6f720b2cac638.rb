class Bob
  def hey(message)
    case
    when silence?(message)
      'Fine. Be that way!'
    when shout?(message)
      'Woah, chill out!'
    when question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(message)
    message.strip.empty?
  end

  def shout?(message)
    message.upcase == message && include_word?(message)
  end

  def question?(message)
    message.end_with? '?'
  end

  def include_word?(message)
    !message.scan(/[A-Z]+/).empty?
  end
end

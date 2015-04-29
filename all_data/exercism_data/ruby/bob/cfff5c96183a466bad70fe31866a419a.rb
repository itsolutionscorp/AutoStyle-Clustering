class Bob
  def hey(message)
    if rude?(message)
      'Woah, chill out!'
    elsif dumb_question?(message)
      'Sure.'
    elsif getting_silent_treatment?(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def rude?(message)
    message.match(/[a-zA-Z]/) && message.upcase == message
  end

  def dumb_question?(message)
    message.slice(-1) == '?'
  end

  def getting_silent_treatment?(message)
    message.strip.empty?
  end
end

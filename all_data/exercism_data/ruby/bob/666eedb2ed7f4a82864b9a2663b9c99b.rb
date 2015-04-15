class Bob
  def hey(message)
    case
    when empty?(message)
      'Fine. Be that way!'
    when shouting?(message)
      'Woah, chill out!'
    when question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def empty?(message)
    message.nil? || message.strip.empty?
  end

  def shouting?(message)
    shouted = message.upcase
    shouted == message && shouted != message.downcase
  end

  def question?(message)
    message.end_with?('?')
  end
end

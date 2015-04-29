class Bob
  def hey(message)
    reply_for(message)
  end

  private

  REPLY_RULES = {
    :empty? => 'Fine. Be that way!',
    :shout? => 'Woah, chill out!',
    :question? => 'Sure.',
    :unknown? => 'Whatever.'
  }

  def reply_for(message)
    REPLY_RULES.find { |rule, reply| send(rule, message) }[1]
  end

  def empty?(message)
    message.strip.empty?
  end

  def shout?(message)
    message.upcase == message
  end

  def question?(message)
    message.strip.end_with?('?')
  end

  def unknown?(message)
    true
  end
end

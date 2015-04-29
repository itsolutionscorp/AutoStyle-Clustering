class Bob
  def hey(message)
    message = Message.new(message)

    return 'Fine. Be that way!' if message.blank?
    return 'Woah, chill out!' if message.shouting?
    return 'Sure.' if message.question?

    'Whatever.'
  end
end

class Message < Struct.new(:message)
  def blank?
    message !~ /[^[:space:]]/
  end

  def shouting?
    message.upcase == message
  end

  def question?
    message[-1] == '?'
  end
end

class Bob
  def hey(message)
    message = Message.new(message)

    return 'Fine. Be that way!' if message.blank?
    return 'Woah, chill out!' if message.is_shouting?
    return 'Sure.' if message.is_question?

    'Whatever.'
  end
end

class Message < Struct.new(:message)
  def blank?
    message.strip.empty?
  end

  def is_shouting?
    message.upcase == message
  end

  def is_question?
    message.end_with?('?')
  end
end

class Bob
  def hey(message)
    Message.respond_to_message(message)
  end
end

Message = Struct.new(:message) do
  def self.respond_to_message(message)
    new(message).response
  end

  def response
    if silence?
      'Fine. Be that way.'
    elsif shouting?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silence?
    message.nil? or message.empty?
  end

  def shouting?
    message.upcase == message
  end

  def question?
    message.end_with?('?')
  end
end

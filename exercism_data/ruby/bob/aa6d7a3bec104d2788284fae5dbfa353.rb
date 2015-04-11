class Bob
  def hey(message)
    Message.respond_to_message(message, normal:   'Whatever.',
                                        shouting: 'Woah, chill out!',
                                        question: 'Sure.',
                                        silence:  'Fine. Be that way.')
  end
end

Message = Struct.new(:message) do
  def self.respond_to_message(message, responses)
    new(message).response(responses)
  end

  def response(responses)
    if silence?
      responses.fetch(:silence)
    elsif shouting?
      responses.fetch(:shouting)
    elsif question?
      responses.fetch(:question)
    else
      responses.fetch(:normal)
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

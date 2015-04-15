class Bob
  attr_reader :responses

  def initialize
    @responses = { normal:   'Whatever.',
                   shouting: 'Woah, chill out!',
                   question: 'Sure.',
                   silence:  'Fine. Be that way.' }
  end

  def hey(message, message_type = MessageType)
    responses.fetch(message_type.for_message(message))
  end
end

MessageType = Struct.new(:message) do
  def self.for_message(message)
    new(message).type
  end

  def type
    if    silence?  then :silence
    elsif shouting? then :shouting
    elsif question? then :question
    else                 :normal
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

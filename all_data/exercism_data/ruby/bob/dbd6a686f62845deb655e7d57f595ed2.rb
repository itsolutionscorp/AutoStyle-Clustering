class Bob
  attr_accessor :responses

  def initialize
    @responses = Responses.new(SilenceMessage.new('Fine. Be that way.'),
                               ShoutingMessage.new('Woah, chill out!'),
                               QuestionMessage.new('Sure.'),
                               NormalMessage.new('Whatever.'))
  end

  def hey message
    responses.respond_to_message(message)
  end
end

class Responses
  attr_accessor :responses

  def initialize *responses
    @responses = responses
  end

  def respond_to_message message
    responses.find {|response| response.applies_to?(message) }.response
  end
end

NormalMessage = Struct.new(:response) do
  def applies_to? message
    true
  end
end

ShoutingMessage = Struct.new(:response) do
  def applies_to? message
    message.upcase == message
  end
end

QuestionMessage = Struct.new(:response) do
  def applies_to? message
    message.end_with?('?')
  end
end

SilenceMessage = Struct.new(:response) do
  def applies_to? message
    message.nil? or message.empty?
  end
end

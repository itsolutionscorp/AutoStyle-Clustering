class Bob
  attr_reader :conversationalist

  def initialize(conversationalist = TeenageConversationalist.new)
    @conversationalist = conversationalist
  end

  def hey(message_in)
    conversationalist.respond_to(message_in)
  end
end

class TeenageConversationalist
  attr_reader :responses

  def initialize(responses={})
    @responses = {
      shouted_at: 'Woah, chill out!',
      questioned: 'Sure.',
      silence: 'Fine. Be that way!',
      default: 'Whatever.'
    }.merge(responses)
  end

  def respond_to(message_in)
    message = Message.new(message_in)

    if message.shouting?
      responses[:shouted_at]
    elsif message.question?
      responses[:questioned]
    elsif message.silent?
      responses[:silence]
    else
      responses[:default]
    end
  end
end

class Message
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def silent?
    text.nil? || text.strip.empty?
  end

  def question?
    !silent? && text.end_with?('?')
  end

  def shouting?
    !silent? && contains_alpha? && uppercase?
  end

  private

  def contains_alpha?
    text.match(/[[:alpha:]]/)
  end

  def uppercase?
    text == text.upcase
  end
end

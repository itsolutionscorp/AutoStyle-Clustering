class Responder
  def initialize(message_string, responses)
    @message_string = message_string
    @responses = responses
  end

  def silence?
    @message_string.to_s.strip.empty?
  end

  def shouting?
    @message_string.upcase == @message_string
  end

  def question?
    @message_string.end_with? '?'
  end

  def default?
    true
  end

  def response
    [:silence, :shouting, :question, :default].each do |type|
      return @responses[type] if send((type.to_s + '?').to_sym)
    end
  end
end

class Bob
  RESPONSES = {
      silence: 'Fine. Be that way!',
      shouting: 'Woah, chill out!',
      question: 'Sure.',
      default: 'Whatever.'
  }

  def hey(message_string)
    Responder.new(message_string, RESPONSES).response
  end
end

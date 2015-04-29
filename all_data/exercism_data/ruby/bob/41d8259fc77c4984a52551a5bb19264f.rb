class Bob

  def hey(message_text)
    Responder.new(message_text).respond
  end
end

class Responder

  RESPONSES = {
      agreement: 'Sure.',
      anger: 'Fine. Be that way!',
      offence: 'Woah, chill out!',
      default: 'Whatever.'
  }

  def initialize(message_text)
    @message = message_text.strip
  end

  def respond
    case
      when silence?
        RESPONSES[:anger]
      when shouting?
        RESPONSES[:offence]
      when question?
        RESPONSES[:agreement]
      else
        RESPONSES[:default]
    end
  end

  private

  def silence?
    @message.empty?
  end

  def shouting?
    !silence? && (@message == @message.upcase)
  end

  def question?
    @message.end_with? '?'
  end
end

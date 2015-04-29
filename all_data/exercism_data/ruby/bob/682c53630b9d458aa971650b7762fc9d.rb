class Tone
  def self.of(message)
    Parser.new(message).detect_tone
  end

  class Parser
    def initialize(message)
      @message = String(message)
    end

    def detect_tone
      return :silent if blank?
      return :shouting if shouting?
      return :questioning if question?
      :normal
    end

    private

    def blank?
      message == ''
    end

    def shouting?
      message.match(/[A-Z]/) && !message.match(/[a-z]/)
    end

    def question?
      message.end_with? '?'
    end

    def message
      @message
    end
  end
end

class Bob
  def initialize
    use_response_for_tone 'Sure.', :questioning
    use_response_for_tone 'Whatever.', :normal
    use_response_for_tone 'Woah, chill out!', :shouting
    use_response_for_tone 'Fine. Be that way!', :silent
  end

  def hey(message_to_bob)
    respond_based_on_tone Tone.of(message_to_bob)
  end

  private

  def use_response_for_tone(response, tone)
    responses[tone] = response
  end

  def respond_based_on_tone(tone)
    responses.fetch tone
  end

  def responses
    @responses ||= {}
  end
end

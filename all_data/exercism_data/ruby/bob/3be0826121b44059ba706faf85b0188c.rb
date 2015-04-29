class Tone
  def self.of(message)
    Parser.new(message).detect_tone
  end

  class Parser
    attr_reader :message

    def initialize(message)
      @message = message
    end

    def detect_tone
      return 'silent' if blank?
      return 'shouting' if at_least_one_uppercase_and_no_lowercase?
      return 'questioning' if question?
      'normal'
    end

    def blank?
      message.nil? || message == ''
    end

    def at_least_one_uppercase_and_no_lowercase?
      message.match(/[A-Z]/) && !message.match(/[a-z]/)
    end

    def question?
      message.end_with? '?'
    end
  end
end

class Bob
  attr_reader :responses

  def initialize
    @responses = {}

    use_response 'Sure.', when_tone_is: 'questioning'
    use_response 'Whatever.', when_tone_is: 'normal'
    use_response 'Woah, chill out!', when_tone_is: 'shouting'
    use_response 'Fine. Be that way!', when_tone_is: 'silent'
  end

  def hey(message_to_bob)
    respond_based_on_tone Tone.of(message_to_bob)
  end

  def use_response(response, when_tone_is: '')
    responses[when_tone_is] = response
  end

  def respond_based_on_tone(tone)
    responses.fetch tone
  end
end

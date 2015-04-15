class Bob
  def hey(message_string)
    message = Message.new(message_string)
    return 'Fine. Be that way!' if message.is_silence?
    return 'Woah, chill out!' if message.is_shouting?
    return 'Sure.' if message.is_question?
    'Whatever.'
  end

  private

  class Message
    def initialize(message_string)
      @string = message_string || ''
      @alpha_only = string.gsub(/[^a-zA-Z]/, '')
    end

    def is_question?
      string.end_with?('?')
    end

    def is_shouting?
      alpha_only.upcase === alpha_only
    end

    def is_silence?
      alpha_only.empty?
    end

    private

    attr_reader :string
    attr_reader :alpha_only
  end
end

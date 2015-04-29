class Bob
  def hey(message_string)
    message = Message.new(message_string)
    if message.is_silence?
      'Fine. Be that way!'
    elsif message.is_shouting?
      'Woah, chill out!' 
    elsif message.is_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  class Message
    def initialize(message_string)
      @string = message_string.to_s
      @alpha_only = string.gsub(/[^a-zA-Z]/, '')
    end

    def is_question?
      string.end_with?('?')
    end

    def is_shouting?
      alpha_only.upcase == alpha_only
    end

    def is_silence?
      alpha_only.empty?
    end

    private

    attr_reader :string
    attr_reader :alpha_only
  end
end

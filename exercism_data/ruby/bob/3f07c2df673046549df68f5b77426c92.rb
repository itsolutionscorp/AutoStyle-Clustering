class Bob
  def hey(message)
    @message = message.strip

    silence_response ||
    yell_response ||
    question_response ||
    default_response
  end

  private

    def default_response
      'Whatever.'
    end

    def silence_response
      'Fine. Be that way!' if @message.empty?
    end

    def yell_response
      'Woah, chill out!' if @message.upcase == @message
    end

    def question_response
      'Sure.' if @message[-1] == '?'
    end
end
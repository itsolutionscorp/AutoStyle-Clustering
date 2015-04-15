class Bob
  def hey(message)
    @message = message.strip

    respond_to_silence ||
    respond_to_yelling ||
    respond_to_questions ||
    respond_with_whatever
  end

  private

    def respond_with_whatever
      'Whatever.'
    end

    def respond_to_silence
      'Fine. Be that way!' if @message.empty?
    end

    def respond_to_yelling
      'Woah, chill out!' if @message.upcase == @message
    end

    def respond_to_questions
      'Sure.' if @message.end_with? '?'
    end
end

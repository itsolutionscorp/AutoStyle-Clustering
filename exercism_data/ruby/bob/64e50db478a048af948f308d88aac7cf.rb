class Bob

  class Message

    def initialize(text)
      @text = text
    end

    def status
      return :blank if blank?
      return :yelling if yelling?
      return :question if question?
      :normal
    end

  private
    def blank?
      @text.nil? || @text.strip.empty?
    end

    def yelling?
      @text.upcase == @text
    end

    def question?
      @text.end_with?('?')
    end
  end

  RESPONSES = {
    blank: 'Fine. Be that way!',
    yelling: 'Woah, chill out!',
    question: 'Sure.',
    normal: 'Whatever.'
  }

  def hey(text)
    message = Message.new(text)
    RESPONSES[message.status]
  end

end

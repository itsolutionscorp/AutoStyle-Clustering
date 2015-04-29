class Bob
  def hey(text)
    respond(Message.new(text))
  end

  def respond(message)
    case
      when message.not_saying_anything? then "Fine. Be that way!"
      when message.yelling?             then "Woah, chill out!"
      when message.asking_question?     then "Sure."
      else                                   "Whatever."
    end
  end

  class Message
    attr_reader :text

    def initialize(text)
      @text = text
    end

    def asking_question?
      text.end_with?("?")
    end

    def yelling?
      text.upcase == text
    end

    def not_saying_anything?
      text.strip.empty?
    end
  end
end

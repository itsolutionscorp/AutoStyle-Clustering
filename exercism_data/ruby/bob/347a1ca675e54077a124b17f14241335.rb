class Bob

  RESPONSES = {
    blank:    'Fine. Be that way!',
    shouting: 'Woah, chill out!',
    question: 'Sure.',
    default:  'Whatever.'
  }

  def hey(msg)
    Message.new(msg).respond(self)
  end

  def respond_to(type)
    RESPONSES.fetch(type)
  end

  private
    class Message < Struct.new(:message)

      def respond(person)
        person.respond_to(type)
      end

      def type
        if blank?
          :blank
        elsif shouting?
          :shouting
        elsif question?
          :question
        else
          :default
        end
      end

      private
      def blank?
        message.strip.empty?
      end

      def shouting?
        message == message.upcase
      end

      def question?
        message.end_with?("?")
      end
    end
end

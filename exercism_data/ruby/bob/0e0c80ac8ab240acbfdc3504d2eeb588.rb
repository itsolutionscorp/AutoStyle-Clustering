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

      TYPES = [
        :blank,
        :shouting,
        :question,
      ]

      def respond(person)
        person.respond_to(type)
      end

      private
      def message
        super.to_s
      end

      def blank?
        message.strip.empty?
      end

      def shouting?
        message == message.upcase
      end

      def question?
        message.end_with?("?")
      end

      def type
        TYPES.detect{|type| send("#{type}?") } || :default
      end
    end
end

class Bob
  def hey(msg)
    Message.new(msg).respond(self)
  end

  def respond_to_blank_message
    'Fine. Be that way!'
  end

  def respond_to_shouting
    'Woah, chill out!'
  end

  def respond_to_question
    'Sure.'
  end

  def respond_to_default
    'Whatever.'
  end

  private
    class Message
      def initialize(message)
        @message = strip_new_lines(message.to_s)
      end

      def respond(person)
        if blank?
          person.respond_to_blank_message
        elsif shouting?
          person.respond_to_shouting
        elsif question?
          person.respond_to_question
        else
          person.respond_to_default
        end
      end

      private
      attr_reader :message

      def blank?
        message.strip.empty?
      end

      def shouting?
        message == message.upcase
      end

      def question?
        message.end_with?("?")
      end

      def strip_new_lines(string)
        string.split("\n").join
      end
    end
end

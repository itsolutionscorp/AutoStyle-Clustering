class Bob
  def hey(msg)
    Message.new(msg).respond
  end

  private
    class Message
      def initialize(message)
        @message = strip_new_lines(message.to_s)
      end

      def respond
        if blank?
          'Fine. Be that way!'
        elsif shouting?
          'Woah, chill out!'
        elsif question?
          'Sure.'
        else
          'Whatever.'
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

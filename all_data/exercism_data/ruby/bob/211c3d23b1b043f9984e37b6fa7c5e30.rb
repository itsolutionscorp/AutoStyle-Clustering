class Bob
  def hey(msg)
    message = Message.new(msg)

    if message.blank?
      'Fine. Be that way!'
    elsif message.shouting?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
    class Message < Struct.new(:message)
      def blank?
        clean_message.delete(" ") == ""
      end

      def shouting?
        clean_message == clean_message.upcase
      end

      def question?
        clean_message.end_with?("?")
      end

      private
      def clean_message
        message.split("\n").join
      end
    end
end

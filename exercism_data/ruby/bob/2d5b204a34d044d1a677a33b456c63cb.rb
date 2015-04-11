class Bob
  def hey(input_string)
    message = Message.new(input_string)
    puts "Sanitized: #{message}"
    case
    when message.is_empty?
      "Fine. Be that way!"
    when message.is_yelling?
      "Woah, chill out!"
    when message.is_question?
      "Sure."
    else
      "Whatever."
    end
  end

  private
  class Message < String
    def initialize(input_string)
      super(sanitize(input_string))
    end

    def sanitize(input_string)
      input_string.gsub(/[\n!\.,\s]/, "")
    end

    def is_question?
      end_with?("?")
    end

    def is_yelling?
      match('^[\d\W]*[A-Z]+[A-Z\d\W]*$')
    end

    def is_empty?
      match('^\s*$')
    end
  end
end

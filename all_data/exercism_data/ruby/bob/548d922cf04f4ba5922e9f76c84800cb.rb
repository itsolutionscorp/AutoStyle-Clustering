class Bob
  def hey(input)
    message = Message.new(input)
    case
    when message.silent?
      'Fine. Be that way.'
    when message.questioning?
      'Sure.'
    when message.shouting?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private
  class Message < String
    def initialize(message)
      super unless message.nil?
    end

    def silent?
      nil? || empty?
    end

    def questioning?
      end_with?('?')
    end

    def shouting?
      eql?(upcase)
    end
  end
end

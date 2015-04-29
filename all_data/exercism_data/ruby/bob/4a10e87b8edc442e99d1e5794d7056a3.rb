class Bob
  def hey(message)
    Request.new(message).respond
  end

  class Request < Struct.new(:message)
    def respond
      case
      when empty?;    'Fine. Be that way!'
      when all_caps?; 'Woah, chill out!'
      when question?; 'Sure.'
      else            'Whatever.'
      end
    end

    private

    def empty?
      message.nil? || only_whitespace?
    end

    def only_whitespace?
      message.strip.empty?
    end

    def all_caps?
      message.upcase == message
    end

    def question?
      message.end_with?('?')
    end
  end
end

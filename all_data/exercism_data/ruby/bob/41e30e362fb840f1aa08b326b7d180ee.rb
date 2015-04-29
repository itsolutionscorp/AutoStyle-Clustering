class Bob
  def hey(text)
    message = Message.new(text)
    return 'Fine. Be that way!' if message.nothing?
    return 'Woah, chill out!' if message.shout?
    return 'Sure.' if message.question?
    'Whatever.'
  end

  private

  class Message < Struct.new(:text)
    def shout?
      text == text.upcase
    end

    def question?
      text.end_with? '?'
    end

    def nothing?
      text.nil? or text.empty?
    end
  end
end

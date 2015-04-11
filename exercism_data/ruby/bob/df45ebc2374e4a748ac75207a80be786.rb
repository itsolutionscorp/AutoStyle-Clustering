class Bob
  def hey(text)
    message = Message.new(text)
    return 'Fine. Be that way!' if message.nothing?
    return 'Woah, chill out!' if message.shout?
    return 'Sure.' if message.question?
    'Whatever.'
  end

  class Message < Struct.new(:text)
    def nothing?
      text.nil? || text.empty?
    end

    def shout?
      !nothing? && text == text.upcase
    end

    def question?
      !nothing? && text.end_with?('?')
    end
  end
end

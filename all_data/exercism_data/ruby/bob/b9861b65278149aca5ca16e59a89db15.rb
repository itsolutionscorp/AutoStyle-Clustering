class Bob

  RESPONSES = {
      empty_message_response:         'Fine. Be that way!',
      shouty_message_response:        'Woah, chill out!',
      interrogative_message_response: 'Sure.',
      default_response:               'Whatever.'
    }

  attr_accessor *RESPONSES.keys

  def initialize(responses = {})
    RESPONSES.merge(responses).each {|key, value| self.send("#{key}=", value)}
  end

  def hey(message)
    message = Message.new(message)
    if message.blank?
      empty_message_response
    elsif message.shouty?
      shouty_message_response
    elsif message.interrogative?
      interrogative_message_response
    else
      default_response
    end
  end

  class Message
    attr :content

    def initialize(content)
      @content = content.to_s
    end

    def blank?
      content.strip.empty?
    end

    def shouty?
      content == content.upcase
    end

    def interrogative?
      content.end_with?('?')
    end
  end
end

module Matchers
  def self.message_type(message)
    matchers.find { |_, matcher| matcher[message] }.first
  end

  def self.matcher(name, &block)
    matchers[name] = block
  end
  private_class_method :matcher

  def self.matchers
    @matchers ||= {}
  end
  private_class_method :matchers


  matcher(:silence) { |message| message.strip.empty? }

  matcher(:shouting) { |message|
    letters = message.gsub(/[^A-Za-z]/, '').chars
    letters.all? { |chr| ('A'..'Z').include?(chr) } && !letters.empty?
  }

  matcher(:question) { |message| message[-1] == '?' }

  matcher(:everything_else) { true }
end

class Responses
  attr_reader :response_mapping
  private :response_mapping
  def initialize(response_mapping)
    @response_mapping = response_mapping
  end

  def response_for(message)
    message_type = Matchers.message_type(message)
    response_mapping.fetch(message_type)
  end
end

class Bob
  attr_reader :responses
  private :responses
  def initialize(
    responses: Responses.new(
      {
        :silence => 'Fine. Be that way!',
        :shouting => 'Woah, chill out!',
        :question => 'Sure.',
        :everything_else => 'Whatever.'
      }
    )
  )
    @responses = responses
  end

  def hey(message)
    responses.response_for(message)
  end
end

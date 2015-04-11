class Bob
  attr_accessor :default_response, :responses

  def hey(message)
    find_response(message).respond
  end

  def default_response(response)
    @default_response = response || build_response('Whatever.') { |message| true }
  end

  def build_response(message, &block)
    Response.new(message, &block)
  end

  def add_response=(response)
    @responses << responses
  end

  def responses
    self.add_response = build_response('Fine. Be that way!') { |message| message.strip.empty? }
    self.add_response = build_response('Woah, chill out!') { |message| message == message.upcase }
    self.add_response = build_response('Sure.') { |message| message.end_with? '?' }

    @responses + [default_response]
    # [NothingResponse.new, YellResponse.new, QuestionResponse.new, DefaultResponse.new]
  end

  def find_response(message)
    responses.detect { |response| response.match? message.to_s }
  end

  private :responses, :find_response

  # class DefaultResponse
  #   def match?(message)
  #     true
  #   end

  #   def respond
  #     'Whatever.'
  #   end
  # end

  # class Response
  #   attr_reader :matcher, :respond
  #   def initialize(message, &block)
  #     @respond = message
  #     @matcher = &block
  #   end

  #   def match?(message)
  #     matcher.call(message.to_s)
  #   end
  # end

  # class NothingResponse
  #   def match?(message)
  #     message.strip.empty?
  #   end

  #   def respond
  #     'Fine. Be that way!'
  #   end
  # end

  # class YellResponse
  #   def match?(message)
  #     message == message.upcase
  #   end

  #   def respond
  #     'Woah, chill out!'
  #   end
  # end

  # class QuestionResponse
  #   def match?(message)
  #     message.end_with? '?'
  #   end

  #   def respond
  #     'Sure.'
  #   end
  # end
end

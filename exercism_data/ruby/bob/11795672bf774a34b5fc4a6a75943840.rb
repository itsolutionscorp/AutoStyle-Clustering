class Bob
  attr_accessor :responses

  def hey(message)
    setup_responses
    find_response(message).respond
  end

  def find_response(message)
    responses.detect { |response| response.match? message }
  end

  def responses
    @responses + Array(default_response)
  end

  private :responses, :find_response

  def default_response(response=build_response('Whatever.') { |message| true })
    @default_response = response
  end

  def build_response(message, &block)
    Response.new(message, block)
  end

  def add_response(response)
    @responses ||= []
    @responses << response
  end

  def setup_responses
    load_basic_responses if @responses.nil?
  end

  def load_basic_responses
    self.add_response build_response('Fine. Be that way!') { |message| message.strip.empty? }
    self.add_response build_response('Woah, chill out!') { |message| message == message.upcase }
    self.add_response build_response('Sure.') { |message| message.end_with? '?' }
  end

  class Response
    attr_reader :matcher, :respond
    def initialize(message, code)
      @respond = message
      @matcher = code
    end

    def match?(message)
      matcher.call(message.to_s)
    end
  end
end

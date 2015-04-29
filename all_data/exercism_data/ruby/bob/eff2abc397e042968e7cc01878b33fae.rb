class Bob
  class Response
    def self.for(message)
      responses.find {|resp| resp.matches?(message) }
    end

    def self.register(response, &matcher)
      responses << new(response, &matcher)
    end

    def self.responses
      @response ||= []
    end

    attr_reader :response, :matcher

    def initialize(response, &matcher)
      @response = response
      @matcher = matcher
    end

    def matches?(message)
      matcher.call(message)
    end

    def to_s
      response
    end
  end

  Response.register('Woah, chill out!') {|msg| msg.gsub(/[^A-Za-z]/, '') =~ /\A[A-Z]+\z/ }
  Response.register('Sure.') {|msg| msg =~ /\?\z/ }
  Response.register('Fine. Be that way!') {|msg| msg =~ /\A\s*\z/ }
  Response.register('Whatever.') { true }

  def hey(message)
    Response.for(message).to_s
  end
end

class Bob
  def initialize
    respond.with('Fine. Be that way!') { silent? }
    respond.with('Woah, chill out!')   { shouting? }
    respond.with('Sure.')              { nosy? }
    respond.with('Whatever.')
  end

  def hey(message)
    respond.to(Message.new(message)).response
  end

  private
  def respond
    @responder ||= Responder.new
  end

  class Message
    def initialize(text)
      @text = String(text)
    end

    def shouting?
      text.upcase == text
    end

    def silent?
      text.strip.empty?
    end

    def nosy?
      text.end_with?('?')
    end

    attr_reader :text
    private :text
  end

  class Responder
    def to(message)
      responses.detect { |response| response.matches?(message) }
    end

    def with(response, &matcher)
      matcher ||= proc { true }
      responses << Response.new(response, matcher)
    end

    private
    def responses
      @responses ||= []
    end

    class Response
      def initialize(response, matcher)
        @response = response
        @matcher  = matcher
      end

      attr_reader :response, :matcher
      private :matcher

      def matches?(message)
        message.instance_exec(&matcher)
      end
    end
  end
end

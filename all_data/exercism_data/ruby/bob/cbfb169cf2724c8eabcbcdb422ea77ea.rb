class Bob
  def hey(text)
    Responder.respond_to(text)
  end

  class Responder
    class << self
      def respond_to(text)
        new(text).respond
      end
    end

    ADJECTIVES = %w{silent shouting asking}
    attr_reader :text

    def initialize(text)
      @text = text
    end

    def respond
      ADJECTIVES.each do |adjective|
        return send("respond_to_#{adjective}") if send("#{adjective}?")
      end
      respond_to_default
    end

    private

      def silent? 
        text.to_s.empty?
      end

      def asking?
        text.end_with?("?")
      end

      def shouting?
        text.upcase == text
      end

      def respond_to_silent
        'Fine. Be that way.'
      end

      def respond_to_asking
        "Sure."
      end

      def respond_to_shouting
        "Woah, chill out!"
      end

      def respond_to_default
        "Whatever."
      end
  end
end

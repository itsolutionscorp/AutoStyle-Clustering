class Bob
  def hey(phrase)
    the_phrase = Phrase.new(phrase)

    the_phrase.was(:shouted).
      respond_with { "Woah, chill out!" }

    the_phrase.was(:asked).
      respond_with { "Sure." }

    the_phrase.was(:empty).
      respond_with { "Fine. Be that way!" }

    the_phrase.was(:something_else).
      respond_with { "Whatever." }

    the_phrase.respond
  end

  class Phrase

    class Response
      def respond_with(&response)
        @response = response
      end

      def respond
        @response.call
      end
    end

    def initialize(phrase)
      @phrase = phrase
    end

    def known_type_of_phrase?(type)
      respond_to? type_method_name(type)
    end

    def phrase_is_type?(type)
      public_send type_method_name(type)
    end

    def was(type)
      if known_type_of_phrase?(type) && phrase_is_type?(type)
        @final_response = Response.new
      else
        Response.new
      end
    end

    def respond
      @final_response.respond
    end

    def shouted?
      is_just_numbers = @phrase.gsub(/[0-9\W]/, "").length == 0
      !is_just_numbers && @phrase.upcase == @phrase
    end

    def asked?
      is_asking = /\?\z/
      is_asking.match(@phrase) && !shouted?
    end

    def empty?
      @phrase.strip.length == 0
    end

    def something_else?
      !shouted? && !asked? && !empty?
    end

  private

    def type_method_name(type)
      (type.to_s + "?").to_sym
    end
  end
end

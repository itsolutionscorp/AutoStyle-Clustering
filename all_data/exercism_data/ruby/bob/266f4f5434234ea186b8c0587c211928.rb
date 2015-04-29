class Bob
  def hey(input)
    Responder.new(input).response
  end

  class Responder
    attr_accessor :input

    def initialize(input)
      self.input = input
    end

    def input
      @input ||= ""
    end

    def response
      if input == ""
        response_to_silence
      elsif input == input.upcase
        response_to_anger
      elsif input.end_with? "?"
        response_to_question
      else
        response_to_anything_else
      end
    end

  private

    def response_to_silence
      "Fine. Be that way!"
    end

    def response_to_anger
      "Woah, chill out!"
    end

    def response_to_question
      "Sure."
    end

    def response_to_anything_else
      "Whatever."
    end
  end
end

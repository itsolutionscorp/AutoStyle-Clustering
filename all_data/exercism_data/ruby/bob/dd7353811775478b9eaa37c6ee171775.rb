class Bob
  def hey(input)
    Responder.new(input).response
  end

  class Responder
    attr_accessor :input

    def initialize(input)
      self.input = input
    end

    RESPONSES = {
      silence: "Fine. Be that way!",
      anger: "Woah, chill out!",
      question: "Sure.",
      else: "Whatever.",
    }

    def input
      @input ||= ""
    end

    def response
      RESPONSES.fetch(input_type)
    end

  private

    def input_type
      if input == ""
        :silence
      elsif input == input.upcase
        :anger
      elsif input.end_with? "?"
        :question
      else
        :else
      end
    end

  end
end

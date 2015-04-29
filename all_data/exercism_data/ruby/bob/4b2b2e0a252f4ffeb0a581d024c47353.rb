class Bob
  def hey(sentence)
    Responder.new(sentence).answers
  end

  class Responder
    attr_accessor :sentence

    def initialize(sentence)
      self.sentence = sentence
    end

    ANSWERS = {
      silence: "Fine. Be that way!",
      assert: "Woah, chill out!",
      question: "Sure.",
      else: "Whatever.",
    }

    def sentence
      @sentence ||= ""
    end

    def answers
      ANSWERS.fetch(response_type)
    end

  private

    def response_type
      if sentence == ""
        :silence
      elsif sentence == sentence.upcase
        :assert
      elsif sentence.end_with? "?"
        :question
      elsif sentence.end_with? "!"
      	:assert
      else
        :else
      end
    end

  end
end

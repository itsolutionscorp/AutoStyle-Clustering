class Bob

  def hey(sentence)
    Answer.to SpeechAnalyzer.new(sentence).detect_type
  end

  class SpeechAnalyzer
    attr_reader :sentence

    def initialize(sentence)
      @sentence = sentence
    end

    def detect_type
      nothing_is_said    ||
      shouting_at_me     ||
      asking_me_question
    end

    private

      def nothing_is_said
        :nothing if (sentence.nil? || sentence.empty?)
      end

      def shouting_at_me
        :shouting if (sentence.eql? sentence.upcase)
      end

      def asking_me_question
        :question if sentence.match(/\?\z/)
      end

  end

  class Answer
    def self.to(sentence_type)
      known_answers = { nothing: "Fine. Be that way!",
                        shouting: "Woah, chill out!",
                        question: "Sure." }

      Hash.new("Whatever.").merge(known_answers)[sentence_type]
    end
  end
end

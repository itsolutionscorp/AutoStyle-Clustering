class Bob

  def initialize
    @brain = Brain.new
  end

  def hey(phrase)
    @brain.choose_answer(phrase)
  end

  class Brain

    def choose_answer(phrase)
      sentence = Sentence.new(phrase)
      case
      when sentence.blank? then 'Fine. Be that way!'
      when sentence.uppercase? then 'Woah, chill out!'
      when sentence.question? then 'Sure.'
      else 'Whatever.'
      end
    end

    Sentence = Struct.new(:phrase) do
      def uppercase?
        phrase == phrase.upcase
      end

      def question?
        phrase.end_with?('?')
      end

      def blank?
        phrase.nil? || phrase.strip.empty?
      end
    end
  end
end

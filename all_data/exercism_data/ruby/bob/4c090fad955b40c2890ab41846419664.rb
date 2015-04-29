class Bob
  module Answers
    class Base
      def initialize(phrase)
        @phrase = phrase
      end

      def phrase
        @phrase
      end
    end

    class Whatever < Base
      def match?
        true
      end

      def answer
        'Whatever.'
      end
    end

    class Fine < Base
      def match?
        phrase =~ /\A\s*\z/
      end

      def answer
        'Fine. Be that way!'
      end
    end

    class ChillOut < Base
      def match?
        return unless contains_upcase_letters(phrase)
        phrase == upcase_letters(phrase)
      end

      def answer
        'Woah, chill out!'
      end

      private

      def contains_upcase_letters(phrase)
        phrase =~ /[A-Z]/
      end

      def upcase_letters(phrase)
        phrase.gsub(/[a-z]/) { |match| match.upcase }
      end
    end

    class Sure < Base
      def match?
        phrase =~ /\?\z/
      end

      def answer
        'Sure.'
      end
    end
  end

  def hey(phrase)
    answer_for(phrase).answer
  end

  private

  ANSWER_CLASSES = [
    Answers::Fine,
    Answers::ChillOut,
    Answers::Sure,
    Answers::Whatever
  ]

  def answer_for(phrase)
    ANSWER_CLASSES.each do |klass|
      answer = klass.new(phrase)
      return answer if answer.match?
    end
  end
end

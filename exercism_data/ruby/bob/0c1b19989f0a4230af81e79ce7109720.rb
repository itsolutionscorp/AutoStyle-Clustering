# I know, it's an overkill solution, but I think it provides a nice basis for Bob to evolve his conversation skills ;)

class Bob
  def hey(text)
    Dialogue(text).answer
  end



  module Dialogues
    module Conversions
      def Dialogue(text)
        text = String(text)

        [
          Silence.new,
          Confrontation.new,
          Question.new,
          Blabbering.new,
        ].find {|dialogue| dialogue.react_to?(text)}
      end
    end

    class Question
      def react_to?(text)
        text.end_with?("?")
      end

      def answer
        'Sure.'
      end
    end

    class Confrontation
      def react_to?(text)
        text == text.upcase && !text.empty?
      end

      def answer
        'Woah, chill out!'
      end
    end

    class Silence
      def react_to?(text)
        text.empty?
      end

      def answer
        'Fine. Be that way.'
      end
    end

    class Blabbering
      def react_to?(_)
        true
      end

      def answer
        'Whatever.'
      end
    end
  end


  include Dialogues
  include Dialogues::Conversions
end

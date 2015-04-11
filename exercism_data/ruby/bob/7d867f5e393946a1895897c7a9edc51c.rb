# new approach:
#  - moving Dialogues into Bob's class, because the answers are specific to Bob, Paul might react completly different
#  - moving the conditions to the Dialogue classes themself
#
# pros: each dialogue class now "knows" when it should step in
# cons: still a bit messy Dialogue() conversion method 

class Bob
  def hey(text)
    dialogue = Dialogue(text)
    dialogue.answer
  end

  module Dialogues
    module Conversions
      def Dialogue(text)
        text = String(text)

        [Silence, Confrontation, Question, Blabbering].each do |dialogue_class|
          if dialogue_class.react_to?(text)
            return dialogue_class.new
          end
        end
      end
    end

    class Question
      def self.react_to?(text)
        text.end_with?("?")
      end

      def answer
        'Sure.'
      end
    end

    class Confrontation
      def self.react_to?(text)
        text == text.upcase && !text.empty?
      end

      def answer
        'Woah, chill out!'
      end
    end

    class Silence
      def self.react_to?(text)
        text.empty?
      end

      def answer
        'Fine. Be that way.'
      end
    end

    class Blabbering
      def self.react_to?(_)
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

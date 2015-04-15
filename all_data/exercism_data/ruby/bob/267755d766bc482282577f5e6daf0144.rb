class Bob
  def hey(phrase)
    Moods.all.find { |mood| mood.strikes?(phrase) }.reply
  end
  
  module Moods
    def self.all
      @moods ||= Moods.constants.map { |class_name| Moods.const_get(class_name).new }
    end
    
    class Nonchalant
      def strikes?(phrase)
        phrase =~ /\?$/
      end
      
      def reply
        'Sure.'
      end
    end

    class Combative
      def strikes?(phrase)
        phrase =~ /^[^a-z]+$/
      end

      def reply
        'Woah, chill out!'
      end
    end
    
    class Indignant
      def strikes?(phrase)
        phrase.nil? || phrase.empty?
      end
      
      def reply
        'Fine. Be that way.'
      end
    end
    
    class Dismissive
      def strikes?(phrase)
        (Moods.all - [self]).none? { |mood| mood.strikes? phrase }
      end

      def reply
        'Whatever.'
      end
    end
  end
end

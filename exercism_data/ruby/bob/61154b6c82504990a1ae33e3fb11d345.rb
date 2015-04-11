class Phrase
  attr_reader :content
  
  def initialize(content)
    @content = content
  end
  
  def asking?
    content =~ /\?$/
  end
  
  def telling?
    !(asking? || yelling? || ignoring?)
  end
  
  def yelling?
    content =~ /^[^a-z]+$/
  end
  
  def ignoring?
    content.nil? || content.empty?
  end
end

class Bob
  def hey(phrase)
    Moods.all.find { |mood| mood.strikes?(Phrase.new(phrase)) }.reply
  end
  
  module Moods
    def self.all
      @moods ||= Moods.constants.map { |class_name| Moods.const_get(class_name).new }
    end
    
    class Nonchalant
      def strikes?(phrase)
        phrase.asking?
      end
      
      def reply
        'Sure.'
      end
    end

    class Combative
      def strikes?(phrase)
        phrase.yelling?
      end

      def reply
        'Woah, chill out!'
      end
    end
    
    class Indignant
      def strikes?(phrase)
        phrase.ignoring?
      end
      
      def reply
        'Fine. Be that way.'
      end
    end
    
    class Dismissive
      def strikes?(phrase)
        phrase.telling?
      end

      def reply
        'Whatever.'
      end
    end
  end
end

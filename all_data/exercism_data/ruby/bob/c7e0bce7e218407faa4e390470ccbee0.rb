class Bob
  def hey(p)
    responses_to Phrase.new(p)
  end
  
  def responses_to(phrase)
    case
    when phrase.silent?
      'Fine. Be that way!'
    when phrase.loud?
      'Woah, chill out!'
    when phrase.quizzicial?
      'Sure.'
    else 
      'Whatever.'
    end  
  end
    
  class Phrase
    
    attr_reader :source
    def initialize(phrase)
      @source = phrase
    end
    
    def silent?
      source.strip.empty?
    end
    
    def loud?
      source.strip.upcase == source
    end
    
    def quizzicial?
      source.end_with? '?'
    end
  end
    
end

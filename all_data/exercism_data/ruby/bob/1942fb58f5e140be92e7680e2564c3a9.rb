class Bob
  def hey phrase
    parser = ConversationParser.new phrase

    if parser.silence?
      'Fine. Be that way!'
    elsif parser.forceful?
      'Woah, chill out!'
    elsif parser.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class ConversationParser
    def initialize phrase
      @phrase = phrase.to_s
    end

    def forceful?
      @phrase == @phrase.upcase
    end

    def question?
      @phrase.end_with? '?'
    end

    def silence?
      @phrase.strip.empty?
    end
  end

end

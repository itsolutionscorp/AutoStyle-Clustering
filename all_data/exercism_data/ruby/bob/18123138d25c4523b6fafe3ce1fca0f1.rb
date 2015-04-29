class Bob
  def hey phrase
    @parser = ConversationParser.new phrase

    if @parser.silence?
      'Fine. Be that way!'
    elsif @parser.forcefull?
      'Woah, chill out!'
    elsif @parser.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  class ConversationParser
    def initialize phrase
      @phrase = phrase
    end

    def forcefull?
      @phrase == @phrase.upcase
    end

    def question?
      @phrase.end_with? '?'
    end

    def silence?
      @phrase.to_s.strip.empty?
    end
  end

end

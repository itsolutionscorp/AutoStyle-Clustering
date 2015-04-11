class Bob
  def initialize
    @parser = ConversationParser.new
  end

  def hey phrase
    @parser.phrase = phrase

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

    attr_writer :phrase

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

class Bob

  class Phrase
    def initialize(phrase)
      @phrase = phrase
    end

    def silence?
      @phrase.to_s.empty?
    end

    def shouting?
      @phrase == @phrase.upcase
    end

    def asking?
      @phrase.end_with? '?'
    end
  end

  def hey(string)
    phrase = Phrase.new(string)
    if phrase.silence?
      'Fine. Be that way!'
    elsif phrase.shouting?
      'Woah, chill out!'
    elsif phrase.asking?
      'Sure.'
    else
      'Whatever.'
    end
  end

end

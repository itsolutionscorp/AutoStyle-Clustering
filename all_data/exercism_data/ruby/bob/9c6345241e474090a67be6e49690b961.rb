class Bob
  class GrammarUnit
    def initialize(phrase)
      @phrase = phrase
    end

    def silence?
      @phrase.strip.empty?
    end

    def with_all_caps?
      return false if no_alphabetic_characters?
      @phrase.upcase == @phrase
    end

    def question?
      @phrase[-1] == '?'
    end

    private

    def no_alphabetic_characters?
      @phrase.swapcase == @phrase
    end
  end

  def hey(phrase)
    grammar_unit = GrammarUnit.new(phrase)

    if grammar_unit.silence?
      'Fine. Be that way!'
    elsif grammar_unit.with_all_caps?
      'Woah, chill out!'
    elsif grammar_unit.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

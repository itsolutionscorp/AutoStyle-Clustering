class Bob
  def hey phrase_text
    phrase = Phrase.new phrase_text

    case
      when phrase.is_silence? then 'Fine. Be that way!'
      when phrase.is_shouting? then 'Whoa, chill out!'
      when phrase.is_question? then 'Sure.'
      else 'Whatever.'
    end
  end

  class Phrase
    def initialize text
      @text = text
    end

    def is_silence?
      @text =~ /\A\s*\Z$/
    end

    def is_question?
      @text.end_with? '?'
    end

    def is_shouting?
      all_letters_uppercase?
    end

    def all_letters_uppercase?
      @text.upcase == @text && @text.downcase != @text
    end
  end
end

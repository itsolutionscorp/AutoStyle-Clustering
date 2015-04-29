class Bob
  def hey phrase_text
    phrase = Phrase.new phrase_text

    return 'Fine. Be that way!' if phrase.is_silence?
    return 'Whoa, chill out!' if phrase.is_shouting?
    return 'Sure.' if phrase.is_question?
    'Whatever.'
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
      letters = @text.scan(/[a-z]/i)
      letters.any? && letters.all? {|l| l.upcase == l }
    end
  end
end

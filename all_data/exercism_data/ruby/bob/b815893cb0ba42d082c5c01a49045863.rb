class Bob
  def hey(phrase)
    translation = PhraseTranslation.new(phrase)

    if translation.addressed_without_words?
      'Fine. Be that way.'
    elsif translation.yelling?
      'Woah, chill out!'
    elsif translation.asked_question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class PhraseTranslation
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def addressed_without_words?
    phrase.nil? || phrase.empty?
  end

  def asked_question?
    phrase =~ /\?$/
  end

  def yelling?
    phrase.upcase == phrase
  end
end

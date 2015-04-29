class Bob

  def hey question
    phrase = Phrase.new question

    return 'Fine. Be that way.' if phrase.unspoken?
    return 'Sure.' if phrase.is_a_question?
    return 'Woah, chill out!' if phrase.all_caps?
    return 'Whatever.'
  end

end

class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def unspoken?
    @phrase.nil? || @phrase.empty?
  end

  def is_a_question?
    @phrase.end_with? '?'
  end

  def all_caps?
    @phrase.upcase == @phrase
  end

end

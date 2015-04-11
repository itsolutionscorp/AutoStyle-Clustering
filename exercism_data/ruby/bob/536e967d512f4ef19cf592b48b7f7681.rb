class Bob

  def hey question
    phrase = Phrase.new question

    return 'Fine. Be that way.' if phrase.unspoken?
    return 'Sure.' if phrase.question?
    return 'Woah, chill out!' if phrase.shouted?
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

  def question?
    @phrase.end_with? '?'
  end

  def shouted?
    @phrase.upcase == @phrase
  end

end

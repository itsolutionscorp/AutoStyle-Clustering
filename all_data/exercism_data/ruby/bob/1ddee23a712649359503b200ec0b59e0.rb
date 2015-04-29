class Bob
  def hey(phrase)
    sentence = Sentence.new phrase

    return 'Fine. Be that way!' if sentence.silent?
    return 'Woah, chill out!' if sentence.shouting?
    return 'Sure.' if sentence.question?
    'Whatever.'
  end
end

class Sentence
  def initialize phrase
    @phrase = phrase.strip
  end

  def question?
    @phrase.end_with? '?'
  end

  def shouting?
    @phrase.upcase == @phrase
  end

  def silent?
    @phrase.empty?
  end
end

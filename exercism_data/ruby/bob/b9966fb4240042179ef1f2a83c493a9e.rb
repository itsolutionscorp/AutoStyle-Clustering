class Bob
  def hey(sentence)
    intonation = Intonation.new sentence
    return 'Fine. Be that way!' if intonation.nothing?
    return 'Woah, chill out!' if intonation.shouting?
    return 'Sure.' if intonation.question?
    return 'Whatever.'
  end
end

class Intonation
  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence.to_s
  end

  def nothing?
    sentence.empty?
  end

  def shouting?
    sentence == sentence.upcase
  end

  def question?
    sentence.end_with? '?'
  end
end

class Sentence
  def initialize(text)
    @text = String(text)
  end

  def nothing?
    @text.empty?
  end

  def shouting?
    @text.upcase == @text
  end

  def question?
    @text.end_with?('?')
  end
end

class Bob
  def hey(sentence)
    sentence = Sentence.new(sentence)
    return 'Fine. Be that way.' if sentence.nothing?
    return 'Woah, chill out!'   if sentence.shouting?
    return 'Sure.'              if sentence.question?
    'Whatever.'
  end
end

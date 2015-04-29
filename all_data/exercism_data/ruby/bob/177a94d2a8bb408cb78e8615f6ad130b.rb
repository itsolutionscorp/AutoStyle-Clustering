class Bob
  def hey(sentence)
    Oracle.new(sentence).answer
  end
end

class Oracle
  def initialize(sentence)
    @sentence = sentence
  end

  def empty?
    @sentence.strip.size == 0
  end

  def scream?
    @sentence == @sentence.upcase
  end

  def question?
    @sentence.end_with? "?"
  end

  def answer
    return 'Fine. Be that way!' if empty?
    return 'Woah, chill out!' if scream?
    return 'Sure.' if question?
    'Whatever.'
  end
end

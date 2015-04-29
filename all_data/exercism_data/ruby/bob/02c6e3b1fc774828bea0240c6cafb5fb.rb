class Bob

  def hey(given_sentence)
    sentence = Sentence.new(given_sentence)

    return "Whatever." if sentence.statement?
    return "Woah, chill out!" if sentence.shout?
    return "Sure." if sentence.question?
    return "Fine. Be that way!" if sentence.silent?

  end

end

class Sentence

  def initialize(sentence)
    @sentence = sentence.to_s.strip
  end

  def statement?
    !silent? && !question? && !shout?
  end

  def shout?
    !silent? && @sentence == @sentence.upcase
  end

  def question?
    @sentence.end_with? "?"
  end

  def silent?
    @sentence.length == 0
  end

end

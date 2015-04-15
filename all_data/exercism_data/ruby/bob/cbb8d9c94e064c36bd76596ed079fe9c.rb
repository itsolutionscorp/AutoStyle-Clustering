class Bob
  def hey(words)
    sentence = Sentence.new(words)
    return 'Fine. Be that way!' if sentence.empty?
    return 'Woah, chill out!' if sentence.yell?
    return 'Sure.' if sentence.question?
    return 'Whatever.'
  end
end

class Sentence
  def initialize(sentence)
    @sentence = sentence
  end

  def empty?
    @sentence.rstrip.length == 0
  end

  def yell?
    @sentence.count("a-zA-Z") > 0 and @sentence.upcase == @sentence
  end

  def question?
    @sentence.end_with? '?'
  end
end

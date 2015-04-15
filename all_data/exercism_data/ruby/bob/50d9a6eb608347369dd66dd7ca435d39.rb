class Bob
  def hey(words)
    sentence = Sentence.new(words)
    return 'Fine. Be that way!' if sentence.empty?
    return 'Woah, chill out!' if sentence.yell?
    return 'Sure.' if sentence.question?
    return 'Whatever.'
  end
end

class Sentence < String
  def empty?
    self.rstrip.length == 0
  end

  def yell?
    self.count("a-zA-Z") > 0 and self.upcase == self
  end

  def question?
    self.end_with? '?'
  end
end

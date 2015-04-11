class Bob
  def hey(words)
    sentence = Sentence.new(words)
    return 'Fine. Be that way!' if sentence.silence?
    return 'Woah, chill out!' if sentence.yell?
    return 'Sure.' if sentence.question?
    return 'Whatever.'
  end
end

class Sentence < String
  def silence?
    self.rstrip.empty?
  end

  def yell?
    self[/[A-Z]/] and self.upcase.eql? self
  end

  def question?
    self.end_with? '?'
  end
end

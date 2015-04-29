class Bob
  attr_accessor :sentence

  def hey(sentence)
    self.sentence = sentence
    return 'Fine. Be that way!' if nothing?
    return 'Woah, chill out!' if shouting?
    return 'Sure.' if question?
    return 'Whatever.'
  end


  private
  def nothing?
    sentence.nil? or sentence.empty?
  end

  def shouting?
    sentence == sentence.upcase
  end

  def question?
    sentence.end_with? '?'
  end
end

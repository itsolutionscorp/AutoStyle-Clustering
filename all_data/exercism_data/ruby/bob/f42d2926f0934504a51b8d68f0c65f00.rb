class Bob
  def hey sentence
    sentence = Sentence.new(sentence)
    if sentence.empty?
      'Fine. Be that way!'
    elsif sentence.shouting?
      'Woah, chill out!'
    elsif sentence.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Sentence
  def initialize(sentence)
    @sentence = sentence.to_s
  end

  def empty?
    sentence.empty?
  end

  def shouting?
    sentence == sentence.upcase
  end

  def question?
    sentence.end_with? '?'
  end

  def to_s
    sentence
  end

  private
  attr_reader :sentence
end

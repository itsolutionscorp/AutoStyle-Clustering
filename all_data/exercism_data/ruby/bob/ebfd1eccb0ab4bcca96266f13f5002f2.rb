class Bob
  def hey(sentence)
    sentence = Sentence.new(sentence)

    if sentence.shouting?
      'Woah, chill out!'
    elsif sentence.question?
      'Sure.'
    elsif sentence.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Sentence
  def initialize(sentence)
    @sentence = sentence
  end

  def shouting?
    contains_letters? && uppercase?
  end

  def question?
    sentence.end_with?('?')
  end

  def silence?
    sentence.strip == ''
  end

  private

  attr_reader :sentence

  def contains_letters?
    !!(sentence =~ /[a-zA-Z]/)
  end

  def uppercase?
    sentence == sentence.upcase
  end
end

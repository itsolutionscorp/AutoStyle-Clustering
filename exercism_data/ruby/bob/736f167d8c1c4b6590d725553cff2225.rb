class Bob

  def hey(sentence)
    Sentence.new(sentence).respond
  end

end

class Sentence

  def initialize(sentence)
    @sentence = sentence || ''
  end

  def respond
    if silence?
      'Fine. Be that way!'
    elsif shouting?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silence?
    @sentence.empty?
  end

  def shouting?
    @sentence.upcase == @sentence
  end

  def question?
    @sentence.end_with? '?'
  end

end

class Classifier
  def initialize(sentence)
    @sentence = sentence
  end
  def lowerletters?
    /[a-z]+/.match(@sentence)
  end
  def upperleters?
    /[A-Z]+/.match(@sentence)
  end
  def question?
    /\?\Z/.match(@sentence)
  end
  def silence?
    /\A\s*\Z/.match(@sentence)
  end
end

class Bob
  def hey(sentence)
    clasificador = Classifier.new(sentence)
    case 
    when (!clasificador.lowerletters? && clasificador.upperleters?)
      'Woah, chill out!'
    when clasificador.question?
      'Sure.'
    when clasificador.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

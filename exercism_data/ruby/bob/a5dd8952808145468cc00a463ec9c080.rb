class Bob

  def initialize
    @brain = Brain.new
  end

  def hey(phrase)
    @brain.choose_answer(Sentence.new(phrase))
  end

end

class Brain
  def choose_answer(sentence)
    case
    when sentence.blank? then 'Fine. Be that way!'
    when sentence.uppercase? then 'Woah, chill out!'
    when sentence.question? then 'Sure.'
    else 'Whatever.'
    end
  end
end

class Sentence
  def initialize(sentence)
    @sentence = sentence
  end

  def uppercase?
    @sentence == @sentence.upcase
  end

  def question?
    @sentence.end_with?('?')
  end

  def blank?
    @sentence.nil? || @sentence.strip.empty?
  end
end

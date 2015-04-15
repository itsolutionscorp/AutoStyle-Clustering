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
    answer = 'Whatever.'
    case
    when sentence.blank? then answer = 'Fine. Be that way!'
    when sentence.is_uppercase? then answer = 'Woah, chill out!'
    when sentence.is_question? then answer = 'Sure.'
    end

    answer
  end
end

class Sentence
  def initialize(sentence)
    @sentence = sentence
  end

  def is_uppercase?
    @sentence == @sentence.upcase
  end

  def is_question?
    @sentence.split('').pop == '?'
  end

  def blank?
    @sentence.nil? || @sentence.strip.empty?
  end
end

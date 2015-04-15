class Bob

  def initialize
    @brain = Brain.new
  end

  def hey(sentence)
    @brain.answer(sentence)
  end

end

class Brain
  def answer(sentence)
    @sentence = sentence
    answer = 'Whatever.'
    case
    when blank? then answer = 'Fine. Be that way!'
    when is_uppercase? then answer = 'Woah, chill out!'
    when is_question? then answer = 'Sure.'
    end

    answer
  end

  private

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

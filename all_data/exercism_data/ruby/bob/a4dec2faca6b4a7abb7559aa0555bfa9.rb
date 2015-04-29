class Bob
  def hey(sentence)
    sentence = Sentence.new(sentence)
    if sentence.is_a_question?
      'Sure.'
    elsif sentence.is_a_yell?
      'Woah, chill out!'
    elsif sentence.is_saying_nothing?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  class Sentence < String
    def is_a_question?
      !self.is_a_yell? && self.match(/\?\z/)
    end

    def is_a_yell?
      !self.is_saying_nothing? && !self.match(/[[:lower:]]/)
    end

    def is_saying_nothing?
      self.match(/\A[[:space:]]*\z/)
    end
  end
end

class Bob
  def hey(sentence)
    sentence = Sentence.new(sentence)
    if sentence.is_saying_nothing?
      'Fine. Be that way!'
    elsif sentence.is_a_yell?
      'Woah, chill out!'
    elsif sentence.is_a_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  class Sentence < String
    def is_a_question?
      self.end_with?('?')
    end

    def is_a_yell?
      self.upcase == self
    end

    def is_saying_nothing?
      self.strip.empty?
    end
  end
end

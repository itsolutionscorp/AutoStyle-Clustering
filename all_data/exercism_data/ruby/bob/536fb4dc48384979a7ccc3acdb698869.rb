class Bob

  def hey sentence
    sentence = Sentence::new(sentence)
    give_response(sentence)
  end

  private

  def give_response(sentence)
    if sentence.is_nth_actually
      "Fine. Be that way!"
    elsif sentence.is_yelling
      "Woah, chill out!"
    elsif sentence.is_question
      "Sure."
    else
      "Whatever."
    end
  end
end

class Sentence < String

  def is_nth_actually
    self.strip == ""
  end

  def is_yelling
    self == self.upcase
  end

  def is_question
    self.end_with?("?")
  end
end

class Bob
  def hey string
    if string.strip.empty?
      return answer_empty
    elsif string.chop == string.chop.upcase
      return answer_upcase
    elsif string.split('').last == "?"
      return answer_question
    else
      return answer_default
    end
  end

  def answer_default
    "Whatever."
  end

  def answer_question
    "Sure."
  end

  def answer_upcase
    "Woah, chill out!"
  end

  def answer_empty
    "Fine. Be that way!"
  end

end

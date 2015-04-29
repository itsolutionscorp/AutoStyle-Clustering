class Bob
  def hey(text)
    if nothing_said?(text)
      'Fine. Be that way!'
    elsif being_shouted_at?(text)
      'Woah, chill out!'
    elsif question_asked?(text)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def being_shouted_at?(text)
    text.upcase == text
  end

  def question_asked?(text)
    text[-1] == "?"
  end

  def nothing_said?(text)
    text.strip == ''
  end
end

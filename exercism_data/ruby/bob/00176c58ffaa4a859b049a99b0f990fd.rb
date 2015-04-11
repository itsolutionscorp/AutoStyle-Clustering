class Bob

  def hey text
    if is_empty? text
      "Fine. Be that way!"
    elsif is_upcase_but_not_numeric? text
      "Woah, chill out!"
    elsif is_a_question? text
      "Sure."
    else
      "Whatever."
    end

  end

private

  def is_empty? text
    text.strip.empty?
  end

  def is_upcase_but_not_numeric? text
    text.upcase == text and not (text.upcase == text and text.downcase == text)
  end

  def is_a_question? text
    text.end_with? "?"
  end

end

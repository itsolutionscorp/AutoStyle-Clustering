class Bob
  def text_type(text)
    if(text == text.upcase && text.match("[A-Za-z] "))
      :yell
    elsif(text.end_with? '?')
      :question
    elsif(text.strip == "")
      :nothing
    else
      :other
    end
  end

  def hey(text)
    type = text_type(text)

    case type
    when :question
      "Sure."
    when :yell
      "Woah, chill out!"
    when :nothing
      "Fine. Be that way!"
    when :other
      "Whatever."
    end
  end
end

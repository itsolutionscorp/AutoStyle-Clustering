class Bob

  def hey(text)
    if text.strip.empty?
      'Fine. Be that way!'
    elsif is_shouting?(text) && has_alphabets?(text)
      'Whoa, chill out!'
    elsif is_question?(text)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def is_question?(text)
    text.end_with?('?')
  end

  def has_alphabets?(text)
    text[/[a-zA-Z]/]
  end

  def is_shouting?(text)
    text == text.upcase
  end
end

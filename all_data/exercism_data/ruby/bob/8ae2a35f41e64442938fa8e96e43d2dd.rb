class Bob
  def hey(text)
    case
      when text_is_empty?(text)
        "Fine. Be that way!"
      when text_all_caps?(text)
        "Woah, chill out!"
      when text_is_question?(text)
        "Sure."
      else
        "Whatever."
    end
  end


  private

  def text_all_caps?(text)
    text == text.upcase
  end

  def text_is_question?(text)
    text.end_with?("?")
  end
  
  def text_is_empty?(text)
    remove_whitespace(text.to_s).empty?
  end

  def remove_whitespace(text)
    text.strip
  end
end